package org.csystem.samples.samplecalculatorapp;

public class OperationInfo {
    private final int m_val1;
    private final int m_val2;
    private final char m_op;

    public OperationInfo(int val1, int val2, char op)
    {
        m_val1 = val1;
        m_val2 = val2;
        m_op = op;
    }

    public int getVal1()
    {
        return m_val1;
    }

    public int getVal2()
    {
        return m_val2;
    }

    public char getOp()
    {
        return m_op;
    }
}
