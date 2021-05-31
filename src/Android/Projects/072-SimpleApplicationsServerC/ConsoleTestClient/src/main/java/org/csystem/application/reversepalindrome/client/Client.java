package org.csystem.application.reversepalindrome.client;

import org.csystem.util.Console;
import org.csystem.util.net.NetworkException;
import org.csystem.util.net.TcpUtil;

import java.io.*;
import java.net.Socket;

public class Client {
    private final int m_upperServerPort;
    private final int m_textBasedCalculatorServerPort;
    private final String m_serverHost;

    private void displayMenu()
    {
        Console.writeLine("1. Upper");
        Console.writeLine("2. Upper Hard coded");
        Console.writeLine("3. Text Base Calculator");
        Console.writeLine("4. Exit");
        Console.write("Select option:");
    }

    private void upperProc()
    {
        try (var socket = new Socket(m_serverHost, m_upperServerPort)) {
            for (;;) {
                var str = Console.read("Bir yazı giriniz:");

                TcpUtil.sendStringViaLength(socket, str);

                if ("quit".equals(str.trim()))
                    break;

                Console.writeLine(TcpUtil.receiveStringViaLength(socket));
            }
        }
        catch (NetworkException ex) {
            Console.Error.writeLine("NetworkException:upperProc:%s", ex.getMessage());
        }
        catch (IOException ex) {
            Console.Error.writeLine("IOException:upperProc:%s", ex.getMessage());
        }
    }

    private void upperHardCodedProc()
    {
        try (var socket = new Socket(m_serverHost, m_upperServerPort)) {
            for (;;) {
                var str = Console.read("Bir yazı giriniz:");
                TcpUtil.sendInt(socket, str.length());
                TcpUtil.sendString(socket, str);

                if ("quit".equals(str.trim()))
                    break;

                int length = TcpUtil.receiveInt(socket);
                Console.writeLine(TcpUtil.receiveString(socket, length));
            }
        }
        catch (NetworkException ex) {
            Console.Error.writeLine("NetworkException:upperProc:%s", ex.getMessage());
        }
        catch (IOException ex) {
            Console.Error.writeLine("IOException:upperProc:%s", ex.getMessage());
        }
    }

    private void textBasedCalculatorProc()
    {
        try (var socket = new Socket(m_serverHost, m_textBasedCalculatorServerPort)) {
            for (;;) {
                var str = Console.read("Komutu giriniz:");

                TcpUtil.sendStringViaLength(socket, str);

                if (str.equals("quit"))
                    break;

                int success = TcpUtil.receiveInt(socket);

                if (success == 1)
                    Console.writeLine("Success:%s", TcpUtil.receiveStringViaLength(socket));
                else
                    Console.writeLine("%s", TcpUtil.receiveStringViaLength(socket));
            }
        }
        catch (IOException ex) {
            Console.Error.writeLine("palindromeProc:%s", ex.getMessage());
        }
    }


    private void quitProc()
    {
        Console.writeLine("C and System Programmers Association");
        System.exit(0);
    }

    private void doWorkForOption(int option)
    {
        switch (option) {
            case 1:
                upperProc();
                break;
            case 2:
                upperHardCodedProc();
                break;
            case 3:
                textBasedCalculatorProc();
                break;
            case 4:
                quitProc();
                break;
            default:
                Console.Error.writeLine("Invalid option");
        }
    }

    public Client(int upperServerPort, int textBasedCalculatorServerPort, String serverHost)
    {
        m_upperServerPort = upperServerPort;
        m_textBasedCalculatorServerPort = textBasedCalculatorServerPort;
        m_serverHost = serverHost;
    }

    public void run() throws InterruptedException
    {
        for (;;) {
            try {
                this.displayMenu();
                var option = Console.readInt();
                this.doWorkForOption(option);
            }
            catch (Throwable ex) {
                Console.Error.writeLine("Exception:%d", ex.getMessage());
            }
        }
    }
}
