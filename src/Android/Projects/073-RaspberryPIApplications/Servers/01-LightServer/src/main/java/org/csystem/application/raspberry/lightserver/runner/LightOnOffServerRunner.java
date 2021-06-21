package org.csystem.application.raspberry.lightserver.runner;

import org.csystem.application.raspberry.lightserver.server.LightInfoServer;
import org.csystem.application.raspberry.lightserver.server.LightOnOffServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class LightOnOffServerRunner implements ApplicationRunner {
    private final LightOnOffServer m_lightOnOffServer;

    public LightOnOffServerRunner(LightOnOffServer lightOnOffServer)
    {
        m_lightOnOffServer = lightOnOffServer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_lightOnOffServer.run();
    }
}
