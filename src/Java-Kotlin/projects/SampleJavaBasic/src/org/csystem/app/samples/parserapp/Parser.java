package org.csystem.app.samples.parserapp;

public class Parser {
    private ISource m_source;

    public Parser()
    {
    }

    public Parser(ISource source)
    {
        this.setSource(source);
    }

    public ISource getSource()
    {
        return m_source;
    }

    public void setSource(ISource source)
    {
        //...
        m_source = source;
    }

    public void doParse()
    {
        if (m_source == null)
            return;

        int count = 0;
        int ch;

        while ((ch = m_source.getChar()) != -1)
            if (Character.isWhitespace((int)ch))
                ++count;

        System.out.printf("Count:%d%n", count);
    }
}
