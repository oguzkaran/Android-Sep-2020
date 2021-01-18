package org.csystem.samples.samplecalculatorapp.component;

import org.csystem.samples.samplecalculatorapp.MainActivity;
import org.csystem.samples.samplecalculatorapp.module.CalculatorModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CalculatorModule.class})
public interface CalculatorApplicationComponent {
    void inject(MainActivity mainActivity);
}
