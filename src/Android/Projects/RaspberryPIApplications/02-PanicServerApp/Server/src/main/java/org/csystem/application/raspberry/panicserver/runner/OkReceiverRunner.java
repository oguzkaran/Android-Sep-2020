package org.csystem.application.raspberry.panicserver.runner;

import org.csystem.application.raspberry.panicserver.receiver.OkReceiver;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class OkReceiverRunner implements ApplicationRunner {
    private final OkReceiver m_okReceiver;

    public OkReceiverRunner(OkReceiver okReceiver)
    {
        m_okReceiver = okReceiver;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_okReceiver.run();
    }
}
