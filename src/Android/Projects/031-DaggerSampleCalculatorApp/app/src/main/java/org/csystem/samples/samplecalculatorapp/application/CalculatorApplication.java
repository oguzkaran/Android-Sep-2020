package org.csystem.samples.samplecalculatorapp.application;

import android.app.Application;

import org.csystem.samples.samplecalculatorapp.component.CalculatorApplicationComponent;
import org.csystem.samples.samplecalculatorapp.component.DaggerCalculatorApplicationComponent;
import org.csystem.samples.samplecalculatorapp.module.CalculatorModule;
import org.csystem.samples.samplecalculatorapp.operation.AddOperation;
import org.csystem.samples.samplecalculatorapp.operation.MultiplyOperation;

public class CalculatorApplication extends Application {
    public static final CalculatorApplicationComponent COMPONENT = DaggerCalculatorApplicationComponent
            .builder()
            .calculatorModule(new CalculatorModule(new AddOperation(), new MultiplyOperation()))
            .build();
}
