package org.csystem.application.reversepalindrome.server.configuration;

import org.csystem.application.reversepalindrome.server.ClientInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class ClientsListConfig {
    @Bean("reverseClientsList")
    public List<ClientInfo> getReverseClientsList()
    {
        return Collections.synchronizedList(new ArrayList<ClientInfo>());
    }

    @Bean("palindromeClientsList")
    public List<ClientInfo> getPalindromeClientsList()
    {
        return Collections.synchronizedList(new ArrayList<ClientInfo>());
    }
}
