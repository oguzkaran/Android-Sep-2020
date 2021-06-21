package org.csystem.application.raspberry.lightserver.server;

import org.csystem.util.Console;
import org.csystem.util.pi.raspberry.raspian.gpio.driver.GPIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

@Component
public class LightOnOffServer {
    private final ExecutorService m_threadPool;
    private final ServerSocket m_serverSocket;

    private void handleClient(Socket socket)
    {
        try (socket) {
            Console.writeLine("Client connected");
            var dis = new DataInputStream(socket.getInputStream());
            var ioNo = dis.readInt();
            var count = dis.readInt();
            var milliSecond = dis.readLong();

            for (int i = 0; i < count; ++i) {
                GPIOUtil.high(ioNo);
                Thread.sleep(milliSecond);
                GPIOUtil.low(ioNo);
                Thread.sleep(milliSecond);
            }
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

    public LightOnOffServer(ExecutorService threadPool,
                            @Qualifier("lightOnOffServerSocket") ServerSocket serverSocket)
    {
        m_threadPool = threadPool;
        m_serverSocket = serverSocket;
    }

    public void run()
    {
        m_threadPool.submit(this::runServer);
    }
}
