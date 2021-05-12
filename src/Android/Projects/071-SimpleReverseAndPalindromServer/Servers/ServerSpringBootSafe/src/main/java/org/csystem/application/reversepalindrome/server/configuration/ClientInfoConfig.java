package org.csystem.application.reversepalindrome.server.configuration;

import org.csystem.application.reversepalindrome.server.runner.ClientInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ClientInfoConfig {
    @Bean("reverseClientsMap")
    public Map<Integer, ClientInfo> getReverseClientsMap()
    {
        return Collections.synchronizedMap(new HashMap<>());
    }

    @Bean("palindromeClientsMap")
    public Map<Integer, ClientInfo> getPalindromeClientsMap()
    {
        return Collections.synchronizedMap(new HashMap<>());
    }
}
