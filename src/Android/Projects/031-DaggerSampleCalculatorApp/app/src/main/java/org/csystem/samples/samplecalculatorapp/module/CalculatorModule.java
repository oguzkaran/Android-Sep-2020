package org.csystem.samples.samplecalculatorapp.module;

import org.csystem.samples.samplecalculatorapp.operation.AddOperation;
import org.csystem.samples.samplecalculatorapp.operation.IOperation;
import org.csystem.samples.samplecalculatorapp.operation.MultiplyOperation;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class CalculatorModule {
    private final AddOperation m_addOperation;
    private final MultiplyOperation m_multiplyOperation;

    public CalculatorModule(AddOperation addOperation, MultiplyOperation multiplyOperation)
    {
        m_addOperation = addOperation;
        m_multiplyOperation = multiplyOperation;
    }

    @Singleton
    @Provides
    public Collection<IOperation> provideOperations()
    {
        return new ArrayList<IOperation>(){{add(m_addOperation); add(m_multiplyOperation);}};
    }
}
