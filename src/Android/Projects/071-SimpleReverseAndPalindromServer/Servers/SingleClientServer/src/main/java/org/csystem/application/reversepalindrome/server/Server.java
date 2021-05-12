package org.csystem.application.reversepalindrome.server;

import org.csystem.util.Console;
import org.csystem.util.StringUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int m_reverseServerPort;
    private final int m_palindromeServerPort;

    private void handleReverseThread(Socket socket)
    {
        try (socket) {
            Console.writeLine("Reverse client connected-%s:%d", socket.getInetAddress().getHostAddress(), socket.getPort());
            var bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            var str = bufferedReader.readLine();

            if (str.charAt(str.length() - 1) == '\r')
                str = str.substring(0, str.length() - 1);

            str = new StringBuilder(str).reverse().toString();

            bufferedWriter.write(str + "\r\n");
            bufferedWriter.flush();
        }
        catch (IOException ex) {
            Console.Error.writeLine("handleReverseThread:%s", ex.getMessage());
        }
    }

    private void handlePalindromeThread(Socket socket)
    {
        try (socket) {
            Console.writeLine("Palindrome client connected-%s:%d", socket.getInetAddress().getHostAddress(), socket.getPort());
            var bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var dataOutputStream = new DataOutputStream(socket.getOutputStream());

            var str = bufferedReader.readLine();

            if (str.charAt(str.length() - 1) == '\r')
                str = str.substring(0, str.length() - 1);

            var result = StringUtil.isPalindrome(str);

            dataOutputStream.writeBoolean(result);
        }
        catch (IOException ex) {
            Console.Error.writeLine("handlePalindromeThread:%s", ex.getMessage());
        }
    }

    private void reverseServerProc()
    {
        try (var serverSocket = new ServerSocket(m_reverseServerPort)){
            Console.writeLine("Reverse server started:");

            for (;;) {
                Console.writeLine("Reverse server is waiting for a client on port:%d", m_reverseServerPort);
                var clientSocket = serverSocket.accept();

                handleReverseThread(clientSocket);
            }
        }
        catch (IOException ex) {
            Console.Error.writeLine("reverseServerProc:%s", ex.getMessage());
        }
    }

    private void palindromeServerProc()
    {
        try (var serverSocket = new ServerSocket(m_palindromeServerPort)){
            Console.writeLine("Palindrome server started:");

            for (;;) {
                Console.writeLine("Palindrome server is waiting for a client on port:%d", m_palindromeServerPort);
                var clientSocket = serverSocket.accept();

                handlePalindromeThread(clientSocket);
            }
        }
        catch (IOException ex) {
            Console.Error.writeLine("palindromeServerProc:%s", ex.getMessage());
        }
    }

    public Server(int reverseServerPort, int palindromeServerPort)
    {
        m_reverseServerPort = reverseServerPort;
        m_palindromeServerPort = palindromeServerPort;
    }

    public void run() throws InterruptedException
    {
        var reverseServerThread = new Thread(this::reverseServerProc);
        var palindromeServerThread = new Thread(this::palindromeServerProc);

        Console.writeLine("C and System Programmers Association");

        reverseServerThread.start();
        palindromeServerThread.start();
        reverseServerThread.join();
        palindromeServerThread.join();
    }
}
