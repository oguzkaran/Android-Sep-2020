/*----------------------------------------------------------------------
	FILE        : GPIOUtil.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 21.06.2021

	Utility class for GPIO

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.pi.raspberry.raspian.gpio.driver;

import org.csystem.util.pi.gpio.exception.GPIOException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public final class GPIOUtil {
    private static int availableIoNosCallback(File f)
    {
        var name = f.getName();
        var ioNoStr = name.substring(name.lastIndexOf('o') + 1);

        return Integer.parseInt(ioNoStr);
    }

    private static boolean isAvailable(int ioNo, String direction)
    {
        boolean result;

        try {
            result = Files.readString(Path.of("/sys/class/gpio/gpio" + ioNo + "/direction")).trim().equals(direction);
        }
        catch (IOException ignore) {
            result = false;
        }

        return result;
    }

    private GPIOUtil()
    {}

    public static boolean exists(int ioNo)
    {
        return new File("/sys/class/gpio/gpio" + ioNo).exists();
    }

    public static boolean isAvailableForOut(int ioNo)
    {
        return isAvailable(ioNo, "out");
    }

    public static boolean isAvailableForIn(int ioNo)
    {
        return isAvailable(ioNo, "in");
    }

    public static int [] getAvailableIoNos()
    {
        var files = new File("/sys/class/gpio")
                .listFiles(f -> f.getName().startsWith("gpio") && !f.getName().startsWith("gpiochip"));

        if (files == null)
            return null;

        return Stream.of(Objects.requireNonNull(files)).mapToInt(GPIOUtil::availableIoNosCallback).toArray();
    }

    public static int [] getAvailableOutIoNos()
    {
        var ioNos = getAvailableIoNos();

        if (ioNos == null)
            return null;

        return Arrays.stream(getAvailableIoNos()).filter(GPIOUtil::isAvailableForOut).toArray();
    }

    public static int [] getAvailableInIoNos()
    {
        var ioNos = getAvailableIoNos();

        if (ioNos == null)
            return null;

        return Arrays.stream(getAvailableIoNos()).filter(GPIOUtil::isAvailableForIn).toArray();
    }

    public static void high(int ioNo)
    {
        try (var fos = new FileOutputStream(String.format("/sys/class/gpio/gpio%d/value", ioNo))) {
            fos.write("1".getBytes(StandardCharsets.UTF_8));
            fos.flush();
        }
        catch (IOException ex) {
            throw new GPIOException("high", ex);
        }
    }

    public static void low(int ioNo)
    {
        try (var fos = new FileOutputStream(String.format("/sys/class/gpio/gpio%d/value", ioNo))) {
            fos.write("0".getBytes(StandardCharsets.UTF_8));
            fos.flush();
        }
        catch (IOException ex) {
            throw new GPIOException("high", ex);
        }
    }
    //...
}
