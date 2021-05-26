package org.csystem.util.pi.raspberry.raspian.gpio.driver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

final class IOUtil {
    private IOUtil()
    {
    }

    static void writeFile(String path, String str) throws IOException
    {
        try (var fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
        }
    }
}
