/*----------------------------------------------------------------------
	FILE        : OutPinInfo.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 18.06.2021

	OutPinInfo class for GPIO out pins

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
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
            if (!GPIOUtil.exists(m_ioNo))
                IOUtil.writeFile("/sys/class/gpio/export", String.valueOf(m_ioNo));

            if (!GPIOUtil.isAvailableForOut(m_ioNo))
                IOUtil.writeFile(String.format("/sys/class/gpio/gpio%d/direction", m_ioNo), "out");
        }
        catch (IOException ex) {
            this.close();
            throw new GPIOException("initialize:", ex);
        }
    }

    private void doLogic(boolean level)
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

    public void logic(boolean active)
    {
        doLogic(active);
    }

    public void high()
    {
        logic(true);
    }

    public void low()
    {
        logic(false);
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

    public void output(byte [] data)
    {
        output(data, data.length);
    }
    public void output(byte [] data, int size)
    {
        try {
            m_fileOutputStreamValue.write(data, 0, size);
        }
        catch (IOException ex) {
            this.close();
            throw new GPIOException("output", ex);
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
