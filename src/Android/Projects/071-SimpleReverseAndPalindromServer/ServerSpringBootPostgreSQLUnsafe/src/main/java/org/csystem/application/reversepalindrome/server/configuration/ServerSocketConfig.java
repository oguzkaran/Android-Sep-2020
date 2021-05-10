package org.csystem.application.reversepalindrome.server.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class ServerSocketConfig {
    @Value("${reverse.port}")
    private int m_reversePort;

    @Value("${palindrome.port}")
    private int m_palindromePort;

    @Bean(name = "reverseServerSocket")
    public ServerSocket getReverseServerSocket() throws IOException
    {
        return new ServerSocket(m_reversePort);
    }

    @Bean(name = "palindromeServerSocket")
    public ServerSocket getPalindromeServerSocket() throws IOException
    {
        return new ServerSocket(m_palindromePort);
    }
}
