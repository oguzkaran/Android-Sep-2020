package org.csystem.application.raspberry.panicserver.runner;

import org.csystem.application.raspberry.panicserver.server.PanicServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PanicServerRunner implements ApplicationRunner {
    private final PanicServer m_panicServer;

    public PanicServerRunner(PanicServer panicServer)
    {
        m_panicServer = panicServer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_panicServer.run();
    }
}
