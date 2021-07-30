package org.csystem.application.raspberry.panicserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class PanicSocketConfig {
    @Value("${panicserver.port}")
    private int m_port;

    @Value("${panicserver.backlog}")
    private int m_backlog;

    @Bean("panicServerSocket")
    public ServerSocket getServerSocket() throws IOException
    {
        return new ServerSocket(m_port, m_backlog);
    }
}
