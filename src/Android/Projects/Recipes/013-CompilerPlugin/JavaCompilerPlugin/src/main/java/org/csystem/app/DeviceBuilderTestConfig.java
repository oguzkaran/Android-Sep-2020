package org.csystem.app;

import org.csystem.app.entity.Device;
import org.csystem.app.entity.DeviceBuilder;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceBuilderTestConfig {
    @Bean
    public ApplicationRunner runDeviceBuilderTest()
    {
        return args -> {
            Device device = new DeviceBuilder()
                    .setId(1)
                    .setName("test")
                    .setHost("192.168.2.145")
                    .build();

            System.out.println(device);
        };
    }

}
