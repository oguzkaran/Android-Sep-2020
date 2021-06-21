package org.csystem.application.raspberry.lightserver.configuration;

import org.csystem.util.Console;
import org.csystem.util.pi.raspberry.raspian.gpio.driver.GPIOUtil;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class GetAvailableIoNosRunnerConfig {
    @Bean
    public ApplicationRunner displayAvailableOutIoNos()
    {
        return args ->  {
            var ioNos = GPIOUtil.getAvailableOutIoNos();

            if (ioNos != null)
                Arrays.stream(ioNos).forEach(no -> Console.write("%d ", no));
            else
                Console.write("No active light");

            Console.writeLine();
        };
    }
}
