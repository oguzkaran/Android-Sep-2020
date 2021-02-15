package org.csystem.samples.samplecalculatorapp.operation;

public interface IOperation {
    int applyAsInt(int a, int b);
    boolean isValid(char op);
}
