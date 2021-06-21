package org.csystem.application.raspberry.lightserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class LightInfoServerSocketConfig {
    @Value("${lightInfoServer.port}")
    private int m_port;
    @Value("${lightInfoServer.backlog}")
    private int m_backlog;

    @Bean("lightInfoServerSocket")
    public ServerSocket getServerSocket() throws IOException
    {
        return new ServerSocket(m_port, m_backlog);
    }
}
