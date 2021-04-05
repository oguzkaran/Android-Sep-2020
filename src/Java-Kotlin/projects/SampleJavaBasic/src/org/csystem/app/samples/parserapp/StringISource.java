package org.csystem.app.samples.parserapp;

public class StringISource implements ISource {
    private final String m_str;
    private int m_index;

    public StringISource(String str)
    {
        m_str = str;
    }

    public int getChar()
    {
        return m_str.length() == m_index ? -1 : m_str.charAt(m_index++);
    }
}
