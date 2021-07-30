package org.csystem.application.raspberry.lightserver.server;

import org.csystem.util.net.TcpUtil;
import org.csystem.util.pi.gpio.exception.GPIOException;
import org.csystem.util.pi.raspberry.raspian.gpio.driver.GPIOUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

@Component
public class LightOnOffServer {
    private final List<CommandInfo> m_commandInfos = new ArrayList<>();
    private final ExecutorService m_threadPool;
    private final ServerSocket m_serverSocket;

    {
        m_commandInfos.add(new CommandInfo(0, this::handleLightOnOff));
        m_commandInfos.add(new CommandInfo(1, this::handleLightOn));
        m_commandInfos.add(new CommandInfo(-1, this::handleLightOff));
    }

    private static class CommandInfo {
        int code;
        Consumer<Socket> consumer;

        public CommandInfo(int code)
        {
            this(code, null);
        }

        public CommandInfo(int code, Consumer<Socket> consumer)
        {
            this.code = code;
            this.consumer = consumer;
        }

        @Override
        public boolean equals(Object other)
        {
            return ((CommandInfo)other).code == code;
        }
    }
    private void handleLightOn(Socket socket)
    {
        try {
            var ioNo = TcpUtil.receiveInt(socket);

            GPIOUtil.high(ioNo);
            TcpUtil.sendInt(socket, 1);
        }
        catch (GPIOException ignore) {
            TcpUtil.sendInt(socket, 0);
        }
    }
    private void handleLightOff(Socket socket)
    {
        try {
            var ioNo = TcpUtil.receiveInt(socket);

            GPIOUtil.low(ioNo);
            TcpUtil.sendInt(socket, 1);
        }
        catch (GPIOException ignore) {
            TcpUtil.sendInt(socket, 0);
        }
    }
    private void handleLightOnOff(Socket socket)
    {
        var ioNo = TcpUtil.receiveInt(socket);
        var count = TcpUtil.receiveInt(socket);
        var milliSecond = TcpUtil.receiveLong(socket);

        lightOnOff(socket, ioNo, count, milliSecond);
    }

    private void lightOnOff(Socket socket, int ioNo, int count, long millisecond)
    {
        try {
            for (int i = 0; i < count; ++i) {
                GPIOUtil.high(ioNo);
                Thread.sleep(millisecond);
                GPIOUtil.low(ioNo);
                Thread.sleep(millisecond);
            }
            TcpUtil.sendInt(socket, 1);
        }
        catch (GPIOException ex) {
            TcpUtil.sendInt(socket, 0);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void handleClient(Socket socket)
    {
        try (socket) {
            var code = TcpUtil.receiveInt(socket);

            int index = m_commandInfos.indexOf(new CommandInfo(code));

            if (index != -1) {
                TcpUtil.sendInt(socket, 1);
                m_commandInfos.get(index).consumer.accept(socket);
            }
            else
                TcpUtil.sendInt(socket, 0);

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
