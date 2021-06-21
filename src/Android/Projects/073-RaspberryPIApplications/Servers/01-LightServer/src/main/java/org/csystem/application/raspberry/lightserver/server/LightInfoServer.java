package org.csystem.application.raspberry.lightserver.server;

import org.csystem.util.pi.raspberry.raspian.gpio.driver.GPIOUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

@Component
public class LightInfoServer {
    private final ExecutorService m_threadPool;
    private final ServerSocket m_serverSocket;

    private void handleClient(Socket socket)
    {
        try (socket) {
            var dos = new DataOutputStream(socket.getOutputStream());
            var ioNos = GPIOUtil.getAvailableOutIoNos();

            dos.writeInt(ioNos.length);

            for (var ioNo : ioNos)
                dos.writeInt(ioNo);

            dos.flush();
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
                m_threadPool.submit(() -> handleClient(clientSocket));
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public LightInfoServer(ExecutorService threadPool,
                           @Qualifier("lightInfoServerSocket") ServerSocket serverSocket)
    {
        m_threadPool = threadPool;
        m_serverSocket = serverSocket;
    }

    public void run()
    {
        m_threadPool.submit(this::runServer);
    }
}
