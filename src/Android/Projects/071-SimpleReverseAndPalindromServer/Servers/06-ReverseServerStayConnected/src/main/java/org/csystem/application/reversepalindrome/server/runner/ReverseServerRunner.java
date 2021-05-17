package org.csystem.application.reversepalindrome.server.runner;

import org.csystem.application.reversepalindrome.server.data.service.ClientInfoService;
import org.csystem.util.Console;
import org.csystem.util.data.service.DataServiceException;
import org.csystem.util.scheduler.Scheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ReverseServerRunner implements ApplicationRunner {
    private final ServerSocket m_serverSocket;
    private final ExecutorService m_threadPool;
    private final ClientInfoService m_clientInfoService;
    private final Map<Socket, ClientInfo> m_clients;

    @Value("${reverse.port}")
    private int m_port;

    @Value("${scheduler.reverse.interval}")
    private long m_schedulerInterval;

    @Value("${scheduler.reverse.threshold}")
    private int m_schedulerThreshold;

    @Value("${scheduler.reverse.intervalunit}")
    private String m_intervalUnitStr;

    @Value("${scheduler.reverse.thresholdunit}")
    private String m_thresholdUnitStr;

    @Value("${reverse.quit.command}")
    private String m_quitCommand;

    @Value("${reverse.client.maxsize}")
    private int m_maxClientSize;

    private int m_nClients;

    private synchronized void setLastUpdate(ClientInfo clientInfo, Socket socket)
    {
        if (!m_clients.containsKey(socket))
            return;

        clientInfo.setLastUpdate(LocalDateTime.now());
    }

    private synchronized boolean checkAndIncrement(BufferedWriter bufferedWriter) throws IOException
    {
        if (m_nClients >= m_maxClientSize) {
            bufferedWriter.write("f\r\n");
            bufferedWriter.flush();
            return false;
        }

        ++m_nClients;
        bufferedWriter.write("s\r\n");
        bufferedWriter.flush();

        return true;
    }

    private synchronized void decrement()
    {
        --m_nClients;
    }

    private void handleReverseThread(Socket socket)
    {
        var status = false;

        try (socket) {
            Console.writeLine("Number of Clients:%d", m_nClients);
            int port = socket.getPort();
            var clientInfo = new ClientInfo(socket, port);

            m_clients.put(socket, clientInfo);
            Console.writeLine("Reverse client connected-%s:%d", socket.getInetAddress().getHostAddress(), port);
            var bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            if (!(status = checkAndIncrement(bufferedWriter)))
                return;

            for (;;) {
                var str = bufferedReader.readLine();

                if (str.trim().equals(m_quitCommand))
                    break;

                this.setLastUpdate(clientInfo, socket);
                var text = str;

                if (str.charAt(str.length() - 1) == '\r')
                    str = str.substring(0, str.length() - 1);

                str = new StringBuilder(str).reverse().toString();

                bufferedWriter.write(str + "\r\n");
                bufferedWriter.flush();
                this.setLastUpdate(clientInfo, socket);
                m_clientInfoService.saveClient(text, true, socket);
            }
        }
        catch (IOException ex) {
            Console.Error.writeLine("handleReverseThread-IOException:%s", ex.getMessage());
        }
        catch (DataServiceException ex) {
            Console.Error.writeLine("handleReverseThread-DataServiceException:%s", ex.getMessage());
        }
        finally {
            m_clients.remove(socket);
            if (status)
                decrement();
        }
    }

    private void reverseSchedulerCallback()
    {
        synchronized (this) {
            m_clients.keySet()
                    .removeIf(socket -> SchedulerUtil.isRemovable(socket, m_clients, m_schedulerThreshold, m_thresholdUnitStr));
        }
    }

    private void reverseServerProc()
    {
        new Scheduler(m_schedulerInterval, TimeUnit.valueOf(m_intervalUnitStr)).schedule(this::reverseSchedulerCallback);

        try (m_serverSocket){
            Console.writeLine("Reverse server started:");

            for (;;) {
                Console.writeLine("Reverse server is waiting for a client on port:%d", m_port);
                var clientSocket = m_serverSocket.accept();

                m_threadPool.submit(() -> handleReverseThread(clientSocket));
            }
        }
        catch (IOException ex) {
            Console.Error.writeLine("reverseServerProc:%s", ex.getMessage());
        }
    }

    public ReverseServerRunner(
            @Qualifier("reverseServerSocket") ServerSocket serverSocket,
            ExecutorService threadPool,
            ClientInfoService clientInfoService,
            @Qualifier("reverseClientsMap") Map<Socket, ClientInfo> clients)
    {
        m_serverSocket = serverSocket;
        m_threadPool = threadPool;
        m_clientInfoService = clientInfoService;
        m_clients = clients;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_threadPool.submit(this::reverseServerProc);
    }
}
