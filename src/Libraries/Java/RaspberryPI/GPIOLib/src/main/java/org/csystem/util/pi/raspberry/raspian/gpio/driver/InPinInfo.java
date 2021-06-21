/*----------------------------------------------------------------------
	FILE        : InPinInfo.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 18.06.2021

	InPinInfo class for GPIO in pins

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.pi.raspberry.raspian.gpio.driver;

import org.csystem.util.pi.gpio.exception.GPIOException;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;

public class InPinInfo implements Closeable {
    private static final int MIN_IO_NO = 0;
    private static final int MAX_IO_NO = 30;

    private final int m_ioNo;
    private final FileInputStream m_fileInputStreamValue;

    private void initPin()
    {
        try {
            IOUtil.writeFile("/sys/class/gpio/export", String.valueOf(m_ioNo));
            IOUtil.writeFile(String.format("/sys/class/gpio/gpio%d/direction", m_ioNo), "in");
        }
        catch (IOException ex) {
            this.close();
            throw new GPIOException("initialize:", ex);
        }
    }

    private void init()
    {
        this.initPin();
    }

    public InPinInfo(int ioNo)
    {
        m_ioNo = ioNo;
        this.init();

        try {
            m_fileInputStreamValue = new FileInputStream(String.format("/sys/class/gpio/gpio%d/value", m_ioNo));
        }
        catch (IOException ex) {
            this.close();
            throw new GPIOException("ctor", ex);
        }
    }

    public int input(byte [] data)
    {
        return input(data, data.length);
    }

    public int input(byte [] data, int size)
    {
        try {
            return m_fileInputStreamValue.read(data, 0, size);
        }
        catch (IOException ex) {
            this.close();
            throw new GPIOException("input", ex);
        }
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
