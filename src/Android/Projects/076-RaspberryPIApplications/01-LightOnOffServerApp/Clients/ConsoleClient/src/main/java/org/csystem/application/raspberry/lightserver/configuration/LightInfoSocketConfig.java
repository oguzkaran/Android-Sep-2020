package org.csystem.application.raspberry.lightserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.net.Socket;

@Configuration
public class LightInfoSocketConfig {
    @Value("${lightInfoServer.host}")
    private String m_host;

    @Value("${lightInfoServer.port}")
    private int m_port;

    @Bean
    @Scope("prototype")
    public Socket getSocket() throws IOException
    {
        return new Socket(m_host, m_port);
    }
}
