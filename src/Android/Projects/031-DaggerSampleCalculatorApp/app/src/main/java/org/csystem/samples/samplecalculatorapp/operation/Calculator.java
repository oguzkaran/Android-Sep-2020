package org.csystem.samples.samplecalculatorapp.operation;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Calculator {
    private final Collection<IOperation> m_operations;

    @Inject
    public Calculator(Collection<IOperation> operations)
    {
        m_operations = operations;
    }

    public int doOperation(int a, int b, char op)
    {
        for (IOperation io : m_operations)
            if (io.isValid(op))
                return io.applyAsInt(a, b);

        throw new UnsupportedOperationException(String.format("%c not supported", op));
    }
}
