package org.csystem.application.raspberry.panicclient.runner;

import org.csystem.util.commandprompt.CommandPrompt;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandPromptRunner implements ApplicationRunner {
    private final CommandPrompt m_commandPrompt;

    public CommandPromptRunner(CommandPrompt commandPrompt)
    {
        m_commandPrompt = commandPrompt;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        new Thread(m_commandPrompt::run).start();
    }
}
