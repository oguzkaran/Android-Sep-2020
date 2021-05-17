package org.csystem.application.reversepalindrome.client;

import org.csystem.util.Console;

import java.io.*;
import java.net.Socket;

public class Client {
    private final int m_reverseServerPort;
    private final int m_palindromeServerPort;
    private final String m_serverHost;

    private void displayMenu()
    {
        Console.writeLine("1. Reverse");
        Console.writeLine("2. Palindrome");
        Console.writeLine("3. Reverse Hack");
        Console.writeLine("4. Palindrome Hack");
        Console.writeLine("5. Exit");
        Console.write("Select option:");
    }

    private void reverseProc()
    {
        var str = Console.read("Bir yazı giriniz:");

        try (var socket = new Socket(m_serverHost, m_reverseServerPort)) {
            var bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            bufferedWriter.write(str + "\r\n");
            bufferedWriter.flush();
            str = bufferedReader.readLine();

            Console.writeLine("Received:%s", str);
        }
        catch (IOException ex) {
            Console.Error.writeLine("reverseProc:%s", ex.getMessage());
        }
    }


    private void palindromeProc()
    {
        var str = Console.read("Bir yazı giriniz:");

        try (var socket = new Socket(m_serverHost, m_palindromeServerPort)) {
            var dataInputStream = new DataInputStream(socket.getInputStream());
            var bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            bufferedWriter.write(str + "\r\n");
            bufferedWriter.flush();
            Console.writeLine(dataInputStream.readBoolean() ? "Palindrom" : "Palindrom değil");
        }
        catch (IOException ex) {
            Console.Error.writeLine("palindromeProc:%s", ex.getMessage());
        }
    }

    private void reverseHackProc()
    {
        try (var socket = new Socket(m_serverHost, m_reverseServerPort)) {
            var bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            var str = Console.read("Bir yazı giriniz:");
            Console.read("");
            bufferedWriter.write(str + "\r\n");
            bufferedWriter.flush();
            Console.read("");
            str = bufferedReader.readLine();
            Console.writeLine("Received:%s", str);
        }
        catch (IOException ex) {
            Console.Error.writeLine("reverseHackProc:%s", ex.getMessage());
        }
    }

    private void palindromeHackProc()
    {
        try (var socket = new Socket(m_serverHost, m_palindromeServerPort)) {
            var dataInputStream = new DataInputStream(socket.getInputStream());
            var bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            var str = Console.read("Bir yazı giriniz:");
            Console.read("");
            bufferedWriter.write(str + "\r\n");
            bufferedWriter.flush();
            Console.read("");
            Console.writeLine(dataInputStream.readBoolean() ? "Palindrom" : "Palindrom değil");
        }
        catch (IOException ex) {
            Console.Error.writeLine("palindromeHackProc:%s", ex.getMessage());
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
                reverseProc();
                break;
            case 2:
                palindromeProc();
                break;
            case 3:
                reverseHackProc();
                break;
            case 4:
                palindromeHackProc();
                break;
            case 5:
                quitProc();
                break;
            default:
                Console.Error.writeLine("Invalid option");

        }
    }

    public Client(int reverseServerPort, int palindromeServerPort, String serverHost)
    {
        m_reverseServerPort = reverseServerPort;
        m_palindromeServerPort = palindromeServerPort;
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
