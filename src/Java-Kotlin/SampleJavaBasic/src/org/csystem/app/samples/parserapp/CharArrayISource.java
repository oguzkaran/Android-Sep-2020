package org.csystem.app.samples.parserapp;

import java.util.Arrays;

public class CharArrayISource implements ISource {
    private final char [] m_chars;
    private int m_index;

    public CharArrayISource(char [] chars)
    {
        m_chars = Arrays.copyOf(chars, chars.length);
    }

    public CharArrayISource(String str)
    {
        m_chars = str.toCharArray();
    }

    public int getChar()
    {
        return m_index == m_chars.length ? -1 : m_chars[m_index++];
    }
}
