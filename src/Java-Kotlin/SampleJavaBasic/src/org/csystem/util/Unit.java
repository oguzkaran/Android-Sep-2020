/*----------------------------------------------------------------------------------------------------------------------
	Unit sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util;

public final class Unit<T> {
    private final T m_value;

    public static <T> Unit<T> of(T value)
    {
        return new Unit<>(value);
    }

    public Unit(T value)
    {
        m_value = value;
    }

    public T getValue()
    {
        return m_value;
    }

    //...

    public String toString()
    {
        return String.format("{value : %s}", m_value);
    }
}
