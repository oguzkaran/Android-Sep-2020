package org.csystem.application.raspberry.panicserver.receiver;

import org.csystem.util.Console;
import org.csystem.util.net.NetworkException;
import org.csystem.util.net.TcpUtil;
import org.csystem.util.net.UdpUtil;
import org.csystem.util.pi.raspberry.raspian.gpio.driver.GPIOUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.concurrent.ExecutorService;

@Component
public class OkReceiver {
    private final ExecutorService m_threadPool;

    @Value("${okreceiver.port}")
    private int m_port;

    @Value("${panicserver.okiono}")
    private int m_ioNo;

    private void handleClient(Socket socket)
    {
        try (socket) {
            var ioNos = GPIOUtil.getAvailableOutIoNos();

            TcpUtil.sendInt(socket, ioNos == null ? 0 : ioNos.length);

            if (ioNos != null && ioNos.length != 0)
                for (var ioNo : ioNos)
                    TcpUtil.sendInt(socket, ioNo);
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    private void runReceiver()
    {
        try {
            for (;;) {
                var value = UdpUtil.receiveInt(m_port);

                Console.writeLine("value=%d", value);
                GPIOUtil.high(m_ioNo);
                Thread.sleep(300);
                GPIOUtil.low(m_ioNo);
            }
        }
        catch (NetworkException ex) {
            Console.Error.writeLine("NetworkException:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Exception: %s, Message: %s", ex.getClass().getName(), ex.getMessage());
        }
    }

    public OkReceiver(ExecutorService threadPool)
    {
        m_threadPool = threadPool;
    }

    public void run()
    {
        m_threadPool.submit(this::runReceiver);
    }
}
