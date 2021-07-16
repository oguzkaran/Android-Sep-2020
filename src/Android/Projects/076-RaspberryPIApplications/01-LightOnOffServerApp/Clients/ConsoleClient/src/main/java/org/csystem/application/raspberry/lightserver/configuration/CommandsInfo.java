package org.csystem.application.raspberry.lightserver.configuration;

import org.csystem.util.Console;
import org.csystem.util.commandprompt.Command;
import org.csystem.util.net.TcpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class CommandsInfo {
    @Value("${lightInfoServer.host}")
    private String m_lightInfoHost;

    @Value("${lightInfoServer.port}")
    private int m_lightInfoPort;

    @Value("${lightOnOffServer.host}")
    private String m_lightOnOffHost;

    @Value("${lightOnOffServer.port}")
    private int m_lightOnOffPort;

    @Command("onoff")
    private void lightOnOff(String ioNo, String count, String millisecond)
    {
        try (var socket = new Socket(m_lightOnOffHost, m_lightOnOffPort)) {
            TcpUtil.sendInt(socket, 0);
            if (TcpUtil.receiveInt(socket) == 0) {
                Console.writeLine("Fail");
                return;
            }
            TcpUtil.sendInt(socket, Integer.parseInt(ioNo));
            TcpUtil.sendInt(socket, Integer.parseInt(count));
            TcpUtil.sendLong(socket, Long.parseLong(millisecond));
            Console.writeLine(TcpUtil.receiveInt(socket) == 1 ? "Success" : "Fail");
        }

        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values");
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    @Command("lg")
    @Command
    private void lights()
    {
        try (var socket = new Socket(m_lightInfoHost, m_lightInfoPort)) {
            var size = TcpUtil.receiveInt(socket);

            for (int i = 0; i < size; ++i)
                Console.writeLine("%d", TcpUtil.receiveInt(socket));
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    private void lightOnOff(String ioNo, int code)
    {
        try (var socket = new Socket(m_lightOnOffHost, m_lightOnOffPort)) {
            TcpUtil.sendInt(socket, code);
            if (TcpUtil.receiveInt(socket) == 0) {
                Console.writeLine("Fail");
                return;
            }
            TcpUtil.sendInt(socket, Integer.parseInt(ioNo));
            Console.writeLine(TcpUtil.receiveInt(socket) == 1 ? "Success" : "Fail");
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values");
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    @Command("on")
    private void lightOn(String ioNo)
    {
        lightOnOff(ioNo, 1);
    }

    @Command("off")
    private void lightOff(String ioNo)
    {
        lightOnOff(ioNo, -1);
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
