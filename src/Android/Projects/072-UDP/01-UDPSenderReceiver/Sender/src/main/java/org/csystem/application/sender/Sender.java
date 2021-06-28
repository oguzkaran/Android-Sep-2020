package org.csystem.application.sender;

import org.csystem.util.Console;

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
        try (var datagramSocket = new DatagramSocket()) {
            for (;;) {
                var text = Console.read("Text:");

                if (text.equals("quit"))
                    break;

                var buf = text.getBytes(StandardCharsets.UTF_8);

                var datagramPacket = new DatagramPacket(buf, 0, buf.length, InetAddress.getByName(m_host), m_port);

                datagramSocket.send(datagramPacket);
            }
        }
        catch (IOException ex) {
            Console.Error.writeLine("IOException:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Receiver any exception:%s", ex.getMessage());
        }
    }
}
