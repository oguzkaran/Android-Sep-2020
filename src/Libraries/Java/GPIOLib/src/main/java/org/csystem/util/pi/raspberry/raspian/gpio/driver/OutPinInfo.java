package org.csystem.util.pi.raspberry.raspian.gpio.driver;

import org.csystem.util.pi.gpio.exception.GPIOException;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class OutPinInfo implements Closeable {
    private static final int MIN_IO_NO = 0;
    private static final int MAX_IO_NO = 30;

    private final int m_ioNo;
    private final FileOutputStream m_fileOutputStreamValue;

    private void initPin()
    {
        try {
            IOUtil.writeFile("/sys/class/gpio/export", String.valueOf(m_ioNo));
            IOUtil.writeFile(String.format("/sys/class/gpio/gpio%d/direction", m_ioNo), "out");
        }
        catch (IOException ex) {
            this.close();
            throw new GPIOException("initialize:", ex);
        }
    }

    private void makeLogic(boolean level)
    {
        try {
            m_fileOutputStreamValue.write(String.format("%d", level ? 1 : 0).getBytes(StandardCharsets.UTF_8));
            m_fileOutputStreamValue.flush();
        }
        catch (IOException ex) {
            this.close();
            throw new GPIOException("output", ex);
        }
    }

    private void init()
    {
        this.initPin();
    }

    public OutPinInfo(int ioNo)
    {
        m_ioNo = ioNo;
        this.init();

        try {
            m_fileOutputStreamValue = new FileOutputStream(String.format("/sys/class/gpio/gpio%d/value", m_ioNo));
        }
        catch (IOException ex) {
            this.close();
            throw new GPIOException("ctor", ex);
        }
    }

    public void activate(boolean active)
    {
        makeLogic(active);
    }

    public void activate()
    {
        activate(true);
    }

    public void deactivate()
    {
        activate(false);
    }

    public boolean ioStatus()
    {
        try (var fileInputStream = new FileInputStream(String.format("/sys/class/gpio/gpio%d/value", m_ioNo))) {
            return fileInputStream.read() == '1';
        }
        catch (IOException ex) {
            this.close();
            throw new GPIOException("ioStatus", ex);
        }
    }

    @Override
    public void close()
    {
        try {
            IOUtil.writeFile("/sys/class/gpio/unexport", String.valueOf(m_ioNo));
        }
        catch (IOException ex) {
            throw new GPIOException("close", ex);
        }
    }
}
