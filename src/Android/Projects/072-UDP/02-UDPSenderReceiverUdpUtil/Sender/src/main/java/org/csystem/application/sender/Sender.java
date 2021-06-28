package org.csystem.application.sender;

import org.csystem.util.Console;
import org.csystem.util.net.NetworkException;
import org.csystem.util.net.UdpUtil;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Sender {
    private final String m_host;
    private final int m_port;

    public Sender(String host, int port)
    {
        m_host = host;
        m_port = port;
    }

    public void run()
    {
        try {
            for (;;) {
                var text = Console.read("Text:");

                if (text.equals("quit"))
                    break;

                UdpUtil.sendString(m_host, m_port, text);
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
