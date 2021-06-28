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

                var text = UdpUtil.receiveString(m_port, BUFFER_SIZE);
                Console.writeLine("Text:%s", text);
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
