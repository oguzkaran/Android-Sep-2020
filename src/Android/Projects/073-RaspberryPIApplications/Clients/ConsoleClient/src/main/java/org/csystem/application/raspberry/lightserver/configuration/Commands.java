package org.csystem.application.raspberry.lightserver.configuration;

import org.csystem.util.Console;
import org.csystem.util.commandprompt.Command;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

@Component
public class Commands {
    @Value("${lightInfoServer.host}")
    private String m_lightInfoHost;

    @Value("${lightInfoServer.port}")
    private int m_lightInfoPort;

    @Value("${lightOnOffServer.host}")
    private String m_lightOnOffHost;

    @Value("${lightOnOffServer.port}")
    private int m_lightOnOffPort;

    private void lightsThreadProc()
    {
        try (var socket = new Socket(m_lightInfoHost, m_lightInfoPort)) {
            var dis = new DataInputStream(socket.getInputStream());

            var size = dis.readInt();

            for (int i = 0; i < size; ++i)
                Console.writeLine("%d", dis.readInt());
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    private void lightOnOffThreadProc(String ioNo, String count, String millisecond)
    {
        try (var socket = new Socket(m_lightOnOffHost, m_lightOnOffPort)) {
            Console.writeLine("Connected");
            var dos = new DataOutputStream(socket.getOutputStream());

            dos.writeInt(Integer.parseInt(ioNo));
            dos.writeInt(Integer.parseInt(count));
            dos.writeLong(Long.parseLong(millisecond));
            dos.flush();
        }
        catch (NumberFormatException ex) {
            Console.Error.writeLine("Invalid values");
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    @Command("lg")
    private void lights() throws InterruptedException
    {
        var thread = new Thread(this::lightsThreadProc);

        thread.start();
        thread.join();
    }

    @Command("lgonoff")
    private void lightOnOff(String ioNo, String count, String millisecond) throws InterruptedException
    {
        var thread = new Thread(() -> lightOnOffThreadProc(ioNo, count, millisecond));

        thread.start();
        thread.join();
    }

    @Command("quit")
    private void exit()
    {
        System.exit(1);
    }

}
