package org.csystem.compilerannotation.entity;

import org.csystem.builder.annotation.BuilderProperty;

public class Device {
    private int m_id;
    private String m_name;
    private String m_host;

    public int getId()
    {
        return m_id;
    }

    @BuilderProperty
    public void setId(int id)
    {
        m_id = id;
    }

    public String getName()
    {
        return m_name;
    }

    @BuilderProperty
    public void setName(String name)
    {
        m_name = name;
    }

    public String getHost()
    {
        return m_host;
    }

    @BuilderProperty
    public void setHost(String host)
    {
        m_host = host;
    }

    @Override
    public String toString()
    {
        return String.format("[%d]%s:%s", m_id, m_name, m_host);
    }
}
