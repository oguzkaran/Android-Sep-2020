package org.csystem.android.compilerannotation.entity;

import androidx.annotation.NonNull;

import org.csystem.builder.annotation.BuilderProperty;

import java.time.LocalDate;
import java.util.Locale;

public class CardInfo {
    private int m_id;
    private String m_ownerName;
    private LocalDate m_expiryDate;

    public int getId()
    {
        return m_id;
    }

    @BuilderProperty
    public void setId(int id)
    {
        m_id = id;
    }

    public String getOwnerName()
    {
        return m_ownerName;
    }

    @BuilderProperty
    public void setOwnerName(String ownerName)
    {
        m_ownerName = ownerName;
    }


    public LocalDate getExpiryDate()
    {
        return m_expiryDate;
    }

    @BuilderProperty
    public void setExpiryDate(LocalDate expiryDate)
    {
        m_expiryDate = expiryDate;
    }

    @NonNull
    @Override
    public String toString()
    {
        return String.format(Locale.getDefault(),"[%d]%s:%s", m_id, m_ownerName, m_expiryDate);
    }
}
