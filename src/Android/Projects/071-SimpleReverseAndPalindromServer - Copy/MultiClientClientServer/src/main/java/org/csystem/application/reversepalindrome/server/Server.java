package org.csystem.application.reversepalindrome.server;

import org.csystem.util.Console;
import org.csystem.util.StringUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Server {
    private final int m_reverseServerPort;
    private final int m_palindromeServerPort;
    private final ExecutorService m_threadPool;

    private static class ThreadUtil {
        public static void sleep(long ms)
        {
            try {
                Thread.sleep(ms);
            }
            catch (InterruptedException ignore) {
            }
        }
    }

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

            ThreadUtil.sleep(ThreadLocalRandom.current().nextLong(10000));

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

            ThreadUtil.sleep(ThreadLocalRandom.current().nextLong(10000));
            dataOutputStream.writeBoolean(result);
            dataOutputStream.flush();
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

                m_threadPool.submit(() -> handleReverseThread(clientSocket));
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

                m_threadPool.submit(() -> handlePalindromeThread(clientSocket));
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
        m_threadPool = Executors.newCachedThreadPool();
    }

    public void run() throws InterruptedException, ExecutionException
    {
        Console.writeLine("C and System Programmers Association");
        var reverseFuture = m_threadPool.submit(this::reverseServerProc);
        var palindromeFuture = m_threadPool.submit(this::palindromeServerProc);

        reverseFuture.get();
        palindromeFuture.get();
        m_threadPool.shutdown();
    }
}
