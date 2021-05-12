package org.csystem.application.reversepalindrome.server.runner;

import org.csystem.application.reversepalindrome.server.ClientInfo;
import org.csystem.application.reversepalindrome.server.data.entity.Client;
import org.csystem.application.reversepalindrome.server.data.service.ClientInfoService;
import org.csystem.util.Console;
import org.csystem.util.StringUtil;
import org.csystem.util.data.service.DataServiceException;
import org.csystem.util.scheduler.Scheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Component
public class ServerApplicationRunner implements ApplicationRunner  {
    private final ServerSocket m_reverseServerSocket;
    private final ServerSocket m_palindromeServerSocket;
    private final ExecutorService m_threadPool;
    private final ClientInfoService m_clientInfoService;
    private final List<ClientInfo> m_reverseClientsList;
    private final List<ClientInfo> m_palindromeClientsList;

    @Value("${reverse.port}")
    private int m_reversePort;

    @Value("${palindrome.port}")
    private int m_palindromePort;

    @Value("${scheduler.reverse.interval}")
    private long m_reverseSchedulerInterval;

    @Value("${scheduler.palindrome.interval}")
    private long m_palindromeSchedulerInterval;

    @Value("${scheduler.reverse.threshold}")
    private int m_reverseSchedulerThreshold;

    @Value("${scheduler.palindrome.threshold}")
    private int m_palindromeSchedulerThreshold;

    private void saveClient(String text, boolean reverse, Socket socket)
    {
        var clientInfo = new Client();

        clientInfo.host = socket.getInetAddress().getHostAddress();
        clientInfo.port = socket.getPort();
        clientInfo.text = text;
        clientInfo.reverse = reverse;
        clientInfo.dateTime = LocalDateTime.now();

        m_clientInfoService.save(clientInfo);
    }

    private void handleReverseThread(Socket socket)
    {
        try (socket) {
            var port = socket.getPort();
            var clientInfo = new ClientInfo(socket, port);

            m_reverseClientsList.add(clientInfo);
            Console.writeLine("Reverse client connected-%s:%d", socket.getInetAddress().getHostAddress(), port);
            var bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            var str = bufferedReader.readLine();

            synchronized (m_reverseClientsList) {
                if (!m_reverseClientsList.contains(new ClientInfo(port)))
                    return;

                clientInfo.setLastUpdate(LocalDateTime.now());
            }
            var text = str;

            if (str.charAt(str.length() - 1) == '\r')
                str = str.substring(0, str.length() - 1);

            str = new StringBuilder(str).reverse().toString();

            bufferedWriter.write(str + "\r\n");
            bufferedWriter.flush();
            saveClient(text, true, socket);
        }
        catch (IOException ex) {
            Console.Error.writeLine("handleReverseThread-IOException:%s", ex.getMessage());
        }
        catch (DataServiceException ex) {
            Console.Error.writeLine("handleReverseThread-DataServiceException:%s", ex.getMessage());
        }
    }

    private void handlePalindromeThread(Socket socket)
    {
        try (socket) {
            var port = socket.getPort();
            var clientInfo = new ClientInfo(socket, port);

            m_palindromeClientsList.add(clientInfo);
            Console.writeLine("Palindrome client connected-%s:%d", socket.getInetAddress().getHostAddress(), port);
            var bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var dataOutputStream = new DataOutputStream(socket.getOutputStream());

            var str = bufferedReader.readLine();

            synchronized (m_palindromeClientsList) {
                if (!m_palindromeClientsList.contains(new ClientInfo(port)))
                    return;

                clientInfo.setLastUpdate(LocalDateTime.now());
            }
            var text = str;
            if (str.charAt(str.length() - 1) == '\r')
                str = str.substring(0, str.length() - 1);

            var result = StringUtil.isPalindrome(str);

            dataOutputStream.writeBoolean(result);
            dataOutputStream.flush();
            saveClient(text, false, socket);
        }
        catch (IOException ex) {
            Console.Error.writeLine("handlePalindromeThread- IOException:%s", ex.getMessage());
        }
        catch (DataServiceException ex) {
            Console.Error.writeLine("handleReverseThread-DataServiceException:%s", ex.getMessage());
        }
    }

    private void schedulerFilterCallback(ClientInfo ci)
    {
        try {
            ci.getSocket().close();
        }
        catch (IOException ignore) {

        }
    }

    private void reverseSchedulerCallback()
    {
        Console.writeLine("Size:%d", m_reverseClientsList.size());
        
        synchronized (m_reverseClientsList) {
            var now = LocalDateTime.now();
            m_reverseClientsList.stream()
                    .filter(ci -> ChronoUnit.SECONDS.between(ci.getLastUpdate(), now) > m_reverseSchedulerThreshold)
                    .forEach(this::schedulerFilterCallback);

            m_reverseClientsList.removeIf(ci -> ChronoUnit.SECONDS.between(ci.getLastUpdate(), now) > m_reverseSchedulerThreshold);
        }
    }

    private void reverseServerProc()
    {
        new Scheduler(m_reverseSchedulerInterval).schedule(this::reverseSchedulerCallback);

        try (m_reverseServerSocket){
            Console.writeLine("Reverse server started:");

            for (;;) {
                Console.writeLine("Reverse server is waiting for a client on port:%d", m_reversePort);
                var clientSocket = m_reverseServerSocket.accept();

                m_threadPool.submit(() -> handleReverseThread(clientSocket));
            }
        }
        catch (IOException ex) {
            Console.Error.writeLine("reverseServerProc:%s", ex.getMessage());
        }
    }

    private void palindromeSchedulerCallback()
    {
        synchronized (m_palindromeClientsList) {
            var now = LocalDateTime.now();
            m_palindromeClientsList.stream()
                    .filter(ci -> ChronoUnit.SECONDS.between(ci.getLastUpdate(), now) > m_palindromeSchedulerThreshold)
                    .forEach(this::schedulerFilterCallback);

            m_palindromeClientsList.removeIf(ci -> ChronoUnit.SECONDS.between(ci.getLastUpdate(), now) > m_palindromeSchedulerThreshold);
        }
    }

    private void palindromeServerProc()
    {
        new Scheduler(m_palindromeSchedulerInterval).schedule(this::palindromeSchedulerCallback);

        try (m_palindromeServerSocket){
            Console.writeLine("Palindrome server started:");

            for (;;) {
                Console.writeLine("Palindrome server is waiting for a client on port:%d", m_palindromePort);
                var clientSocket = m_palindromeServerSocket.accept();

                m_threadPool.submit(() -> handlePalindromeThread(clientSocket));
            }
        }
        catch (IOException ex) {
            Console.Error.writeLine("palindromeServerProc:%s", ex.getMessage());
        }
    }

    public ServerApplicationRunner(
            @Qualifier("reverseServerSocket") ServerSocket reverseServerSocket,
            @Qualifier("palindromeServerSocket") ServerSocket palindromeServerSocket,
            ExecutorService threadPool,
            ClientInfoService clientInfoService,
            @Qualifier("reverseClientsList") List<ClientInfo> reverseClientsList,
            @Qualifier("palindromeClientsList") List<ClientInfo> palindromeClientsList)
    {
        m_reverseServerSocket = reverseServerSocket;
        m_palindromeServerSocket = palindromeServerSocket;
        m_threadPool = threadPool;
        m_clientInfoService = clientInfoService;
        m_reverseClientsList = reverseClientsList;
        m_palindromeClientsList = palindromeClientsList;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        Console.writeLine("C and System Programmers Association");
        m_threadPool.submit(this::reverseServerProc);
        m_threadPool.submit(this::palindromeServerProc);
    }
}
