/*----------------------------------------------------------------------------------------------------------------------
    CSDArrayList sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.collection;

public class CSDArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E [] m_elems;
    private int m_index;

    private static void doWorkForIllegalArgumentException(String message)
    {
        throw new IllegalArgumentException(message);
    }

    private static void doWorkForIndexOutOfBoundsException(String message)
    {
        throw new IndexOutOfBoundsException(message);
    }

    private void checkForIndex(int index)
    {
        if (index < 0 || index >= m_index)
            doWorkForIndexOutOfBoundsException("Index out of bounds");
    }

    private void checkCapacityValue(int capacity)
    {
        if (capacity < 0)
            doWorkForIllegalArgumentException("Capacity can not be negative");
    }

    private void changeCapacity(int capacity)
    {
        E [] temp = (E[]) new Object[capacity];

        for (int i = 0; i < m_index; ++i)
            temp[i] = m_elems[i];

        m_elems = temp;
    }

    public CSDArrayList()
    {
        m_elems = (E []) new Object[DEFAULT_CAPACITY];
    }

    public CSDArrayList(int capacity)
    {
        checkCapacityValue(capacity);

        m_elems = (E[]) new Object[capacity];
    }

    public boolean add(E elem)
    {
        if (m_index == m_elems.length)
            changeCapacity(m_elems.length == 0 ? 1 : m_elems.length * 2);

        m_elems[m_index++] = elem;

        return true;
    }

    public void add(int index, E elem)
    {
        if (m_index == m_elems.length)
            changeCapacity(m_elems.length == 0 ? 1 : m_elems.length * 2);

        //TODO:
    }

    public int capacity()
    {
        return m_elems.length;
    }

    public void clear()
    {
        for (int i = 0; i < m_index; ++i)
            m_elems[i] = null;

        m_index = 0;
    }

    public void ensureCapacity(int minCapacity)
    {
        if (minCapacity <= m_elems.length)
            return;

        changeCapacity(Math.max(minCapacity, m_elems.length * 2));
    }

    public E get(int index)
    {
        checkForIndex(index);

        return m_elems[index];
    }

    public E remove(int index)
    {
        //TODO: bellek sızıntına dikkat!!!
        E oldElem = m_elems[index];

        //...

        return oldElem;
    }

    public E set(int index, E elem)
    {
        checkForIndex(index);
        E oldElem = m_elems[index];

        m_elems[index] = elem;

        return oldElem;
    }

    public int size()
    {
        return m_index;
    }

    public void trimToSize()
    {
        changeCapacity(m_index);
    }

    //...

    public String toString()
    {
        String str = "";

        for (int i = 0; i < m_index; ++i)
            str += m_elems[i] + ", ";

        if (!str.isEmpty())
            str = str.substring(0, str.length() - ", ".length());

        return "[" + str + "]";
    }
}
