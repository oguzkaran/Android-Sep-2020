package org.csystem.application.raspberry.panicserver.server;

import org.csystem.util.net.TcpUtil;
import org.csystem.util.pi.raspberry.raspian.gpio.driver.GPIOUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

@Component
public class PanicServer {
    private final ExecutorService m_threadPool;
    private final ServerSocket m_serverSocket;

    @Value("${panicserver.problemiono}")
    private int m_ioNo;

    private void handleClient(Socket socket)
    {
        try (socket) {
            var code = TcpUtil.receiveInt(socket);

            var result = 1;

            switch (code) {
                case 0:
                    GPIOUtil.high(m_ioNo);
                    break;
                case 1:
                    GPIOUtil.low(m_ioNo);
                    break;
                default:
                    result = -1;
            }

            TcpUtil.sendInt(socket, result);
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    private void runServer()
    {
        try {
            for (;;) {
                var clientSocket = m_serverSocket.accept();

                handleClient(clientSocket);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public PanicServer(ExecutorService threadPool,
                       @Qualifier("panicServerSocket") ServerSocket serverSocket)
    {
        m_threadPool = threadPool;
        m_serverSocket = serverSocket;
    }

    public void run()
    {
        m_threadPool.submit(this::runServer);
    }
}
