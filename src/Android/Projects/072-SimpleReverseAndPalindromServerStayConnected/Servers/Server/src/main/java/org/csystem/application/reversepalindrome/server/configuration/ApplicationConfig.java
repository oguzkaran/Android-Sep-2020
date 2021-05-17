package org.csystem.application.reversepalindrome.server.configuration;

import org.csystem.util.Console;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean("application")
    public ApplicationRunner runApplication()
    {
        return args -> Console.writeLine("C and System Programmers Assocition");
    }
}
