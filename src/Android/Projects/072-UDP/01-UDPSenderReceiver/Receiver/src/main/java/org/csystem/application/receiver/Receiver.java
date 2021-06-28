package org.csystem.application.receiver;

import org.csystem.util.Console;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class Receiver {
    private static final int BUFFER_SIZE = 1024;
    private final int m_port;

    public Receiver(int port)
    {
        m_port = port;
    }

    public void run()
    {
        try (var datagramSocket = new DatagramSocket(m_port)) {
            var buf = new byte[BUFFER_SIZE];

            for (;;) {
                Console.writeLine("Waiting for a sender");
                var datagramPacket = new DatagramPacket(buf, buf.length);

                datagramSocket.receive(datagramPacket);
                var length = datagramPacket.getLength();
                var text = new String(datagramPacket.getData(), 0, length, StandardCharsets.UTF_8);
                var hostAddress = datagramPacket.getAddress().getHostAddress();
                var port = datagramPacket.getPort();

                Console.writeLine("%d bytes data [%s] received from %s:%d", length, text, hostAddress, port);
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
