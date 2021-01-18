package org.csystem.samples.samplecalculatorapp.operation;

public class MultiplyOperation implements IOperation {
    @Override
    public int applyAsInt(int a, int b)
    {
        return a * b;
    }

    @Override
    public boolean isValid(char op)
    {
        return op == '*';
    }
}
