package org.csystem.application.raspberry.panicclient.configuration;

import org.csystem.util.commandprompt.CommandPrompt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CommandConfig {
    private final CommandsInfo m_commandsInfo;

    public CommandConfig(CommandsInfo commandsInfo)
    {
        m_commandsInfo = commandsInfo;
    }

    @Bean
    @Scope("prototype")
    public CommandPrompt getCommandPrompt()
    {
        return new CommandPrompt()
                .setPrompt("csd")
                .setPromptSuffix("$")
                .register(m_commandsInfo);
    }
}
