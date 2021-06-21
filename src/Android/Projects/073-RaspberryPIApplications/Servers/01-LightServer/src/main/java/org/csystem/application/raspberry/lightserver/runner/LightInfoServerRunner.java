package org.csystem.application.raspberry.lightserver.runner;

import org.csystem.application.raspberry.lightserver.server.LightInfoServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class LightInfoServerRunner implements ApplicationRunner {
    private final LightInfoServer m_lightInfoServer;

    public LightInfoServerRunner(LightInfoServer lightInfoServer)
    {
        m_lightInfoServer = lightInfoServer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_lightInfoServer.run();
    }
}
