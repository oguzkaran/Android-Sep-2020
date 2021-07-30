package org.csystem.application.raspberry.panicclient.configuration;

import org.csystem.util.Console;
import org.csystem.util.commandprompt.Command;
import org.csystem.util.net.TcpUtil;
import org.csystem.util.net.UdpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.Optional;

@Component
public class CommandsInfo {
    @Value("${panicServer.host}")
    private String m_panicServerHost;

    @Value("${panicServer.tcpPort}")
    private int m_panicServerTcpPort;

    @Value("${panicServer.udpPort}")
    private int m_panicServerUdpPort;

    private Optional<Thread> m_threadOptional = Optional.empty();
    private boolean m_panic;

    @Command
    private void ok()
    {
        if (m_threadOptional.isEmpty())
            UdpUtil.sendInt(m_panicServerHost, m_panicServerUdpPort, 1);
        else
            Console.Error.writeLine("ok sender is running");
    }

    @Command
    private void ok(String countStr)
    {
        try {
            if (m_threadOptional.isPresent()) {
                Console.Error.writeLine("ok sender is running");
                return;
            }

            var count = Integer.parseInt(countStr);

            while (count-- > 0) {
                ok();
                Thread.sleep(700);
            }
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid count value");
        }
        catch (InterruptedException ignore) {

        }
    }

    private void startOkThreadProc()
    {
        try {
            while (!Thread.interrupted()) {
                UdpUtil.sendInt(m_panicServerHost, m_panicServerUdpPort, 1);
                Thread.sleep(700);
            }
        }
        catch (InterruptedException ignore) {

        }
    }

    @Command("startok")
    private void startOk()
    {
        if (m_panic) {
            Console.Error.writeLine("Panic state");
            return;
        }
        if (m_threadOptional.isPresent()) {
            Console.Error.writeLine("ok sender is running");
            return;
        }

        m_threadOptional = Optional.of(new Thread(this::startOkThreadProc));
        m_threadOptional.get().start();
    }

    @Command("stopok")
    private void stopOk()
    {
        m_threadOptional.ifPresent(t -> {t.interrupt(); m_threadOptional = Optional.empty();});
    }

    @Command
    private void panic()
    {
        m_panic = true;
        stopOk();
        try (var socket = new Socket(m_panicServerHost, m_panicServerTcpPort)) {
            TcpUtil.sendInt(socket, 0);
            Console.writeLine("%s", TcpUtil.receiveInt(socket) == 1 ? "En kısa zamanda yardım gelecek" : "Merkezde problem var");
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    @Command("npanic")
    private void noPanic()
    {
        m_panic = false;
        startOk();
        try (var socket = new Socket(m_panicServerHost, m_panicServerTcpPort)) {
            TcpUtil.sendInt(socket, 1);
            Console.writeLine("%s", TcpUtil.receiveInt(socket) == 1 ? "Panik durumu giderildi" : "Merkezde problem var");
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    @Command
    @Command("cls")
    private void clear()
    {
        for (int i = 0; i < 70; ++i)
            System.out.println();
    }

    @Command("quit")
    @Command
    private void exit()
    {
        System.exit(1);
    }
}
