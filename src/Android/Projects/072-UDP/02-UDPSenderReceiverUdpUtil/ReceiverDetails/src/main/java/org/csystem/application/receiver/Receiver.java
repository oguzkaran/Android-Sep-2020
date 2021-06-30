package org.csystem.application.receiver;

import org.csystem.util.Console;
import org.csystem.util.net.NetworkException;
import org.csystem.util.net.UdpUtil;

public class Receiver {
    private static final int BUFFER_SIZE = 1024;
    private final int m_port;

    public Receiver(int port)
    {
        m_port = port;
    }

    public void run()
    {
        try {
            var buf = new byte[BUFFER_SIZE];

            for (;;) {
                Console.writeLine("Waiting for a sender");

                var packet = UdpUtil.receiveDatagramPacket(m_port, BUFFER_SIZE);
                var length = packet.getLength();
                var text = new String(packet.getData(), 0, length);
                var hostAddress = packet.getAddress().getHostAddress();
                var port = packet.getPort();
                Console.writeLine("%d bytes data [%s] received from %s:%d", length, text, hostAddress, port);
            }
        }
        catch (NetworkException ex) {
            Console.Error.writeLine("IOException:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Receiver any exception:%s", ex.getMessage());
        }
    }
}
