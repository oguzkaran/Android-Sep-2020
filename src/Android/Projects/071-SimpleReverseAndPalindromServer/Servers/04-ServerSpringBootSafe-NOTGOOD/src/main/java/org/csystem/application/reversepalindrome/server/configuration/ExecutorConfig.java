package org.csystem.application.reversepalindrome.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {
    //...
    @Bean
    public ExecutorService getThreadPool()
    {
        return Executors.newCachedThreadPool();
    }
}
