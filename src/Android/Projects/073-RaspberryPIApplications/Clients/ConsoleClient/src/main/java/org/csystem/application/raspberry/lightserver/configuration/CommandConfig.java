package org.csystem.application.raspberry.lightserver.configuration;

import org.csystem.util.commandprompt.CommandPrompt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CommandConfig {
    private final Commands m_commands;

    public CommandConfig(Commands commands)
    {
        m_commands = commands;
    }

    @Bean
    @Scope("prototype")
    public CommandPrompt getCommandPrompt()
    {
        return new CommandPrompt()
                .setPrompt("csd")
                .setPromptSuffix("$")
                .register(m_commands);
    }
}
