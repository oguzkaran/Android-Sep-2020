package org.csystem.application.reversepalindrome.server.configuration;

import org.csystem.application.reversepalindrome.server.runner.ClientInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.Socket;
import java.util.*;

@Configuration
public class ClientInfoConfig {
    @Bean("reverseClientsMap")
    public Map<Socket, ClientInfo> getReverseClientsMap()
    {
        return Collections.synchronizedMap(new HashMap<>());
    }

    @Bean("palindromeClientsMap")
    public Map<Socket, ClientInfo> getPalindromeClientsMap()
    {
        return Collections.synchronizedMap(new HashMap<>());
    }
}
