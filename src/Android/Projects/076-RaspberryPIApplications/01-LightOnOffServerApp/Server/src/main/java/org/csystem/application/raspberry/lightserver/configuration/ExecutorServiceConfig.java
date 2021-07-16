package org.csystem.application.raspberry.lightserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorServiceConfig {
    @Bean
    @Scope("prototype")
    public ExecutorService getExecutorService()
    {
        return Executors.newCachedThreadPool();
    }
}
