package org.csystem.application.raspberry.lightserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class LightOnOffServerSocketConfig {
    @Value("${lightOnOffServer.port}")
    private int m_port;
    @Value("${lightOnOffServer.backlog}")
    private int m_backlog;

    @Bean("lightOnOffServerSocket")
    public ServerSocket getServerSocket() throws IOException
    {
        return new ServerSocket(m_port, m_backlog);
    }
}
