/*----------------------------------------------------------------------------------------------------------------------
	DateTime sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util.datetime;

import java.util.Random;

public class DateTime {
    private final Date m_date;
    private final Time m_time;

    private DateTime(Date date, Time time)
    {
        m_date = date;
        m_time = time;
    }

    //TODO: randomTime metotları
    public static DateTime randomDateTime(Random r, int minYear, int maxYear)
    {
        return new DateTime(Date.randomDate(r, minYear, maxYear), Time.randomTime(r));
    }


    public DateTime() // Örnek için yapıldı. Detaylandırılmadı
    {
        m_date = new Date();
        m_time = new Time();
    }

    public DateTime(int day, int monthValue, int year)
    {
        this(day, monthValue, year, 0, 0);
    }

    public DateTime(int day, Month month, int year)
    {
        this(day, month.ordinal() + 1, year);
    }

    public DateTime(int day, int monthValue, int year, int hour, int minute)
    {
        this(day, monthValue, year, hour, minute, 0);
    }

    public DateTime(int day, Month month, int year, int hour, int minute)
    {
        this(day, month.ordinal() + 1, year, hour, minute);
    }

    public DateTime(int day, int monthValue, int year, int hour, int minute, int second)
    {
        this(day, monthValue, year, hour, minute, second, 0);
    }

    public DateTime(int day, Month month, int year, int hour, int minute, int second)
    {
        this(day, month.ordinal() + 1, year, hour, minute, second);
    }

    public DateTime(int day, Month month, int year, int hour, int minute, int second, int millisecond)
    {
        this(day, month.ordinal() + 1, year, hour, minute, second, millisecond);
    }

    public DateTime(int day, int monthValue, int year, int hour, int minute, int second, int millisecond)
    {
        m_date = new Date(day, monthValue, year);
        m_time = new Time(hour, minute, second, millisecond);
    }

    public int getDay()
    {
        return m_date.getDay();
    }

    public void setDay(int val)
    {
        m_date.setDay(val);
    }

    public int getMonthValue()
    {
        return m_date.getMonthValue();
    }

    public void setMonthValue(int val)
    {
        m_date.setMonthValue(val);
    }

    public Month getMonth()
    {
        return m_date.getMonth();
    }

    public void setMonth(Month month)
    {
        m_date.setMonth(month);
    }

    public int getYear()
    {
        return m_date.getYear();
    }

    public void setYear(int val)
    {
        m_date.setYear(val);
    }

    public DayOfWeek getDayOfWeek()
    {
        return m_date.getDayOfWeek();
    }

    public String getDayOfWeekTR()
    {
        return m_date.getDayOfWeekTR();
    }

    public String getDayOfWeekEN()
    {
        return m_date.getDayOfWeekEN();
    }

    public boolean isLeapYear()
    {
        return m_date.isLeapYear();
    }

    public boolean isWeekday()
    {
        return m_date.isWeekday();
    }

    public boolean isWeekend()
    {
        return m_date.isWeekend();
    }

    public int getHour()
    {
        return m_time.getHour();
    }

    public void setHour(int val)
    {
        m_time.setHour(val);
    }

    public int getMinute()
    {
        return m_time.getMinute();
    }

    public void setMinute(int val)
    {
        m_time.setMinute(val);
    }

    public int getSecond()
    {
        return m_time.getSecond();
    }

    public void setSecond(int val)
    {
        m_time.setSecond(val);
    }

    public int getMillisecond()
    {
        return m_time.getMillisecond();
    }

    public void setMillisecond(int val)
    {
        m_time.setMillisecond(val);
    }

    public String toLongDateStringTR()
    {
        return m_date.toLongDateStringTR();
    }

    public String toLongDateStringEN()
    {
        return m_date.toLongDateStringEN();
    }

    public String toLongTimeString()
    {
        return m_time.toLongTimeString();
    }

    public String toShortTimeString()
    {
        return m_time.toShortTimeString();
    }

    //TODO: toStringXXX metotları
    public String toString(char delim)
    {
        return String.format("%s %s", m_date.toString(delim), m_time);
    }

    public String toString()
    {
        return toString('/');
    }
}
