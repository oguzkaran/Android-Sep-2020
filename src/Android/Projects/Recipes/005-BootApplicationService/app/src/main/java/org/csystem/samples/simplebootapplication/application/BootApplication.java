package org.csystem.samples.simplebootapplication.application;

import android.content.Intent;

import androidx.annotation.NonNull;

import org.csystem.android.application.App;
import org.csystem.samples.simplebootapplication.SampleService;

public class BootApplication extends App {
    @Override
    public void onCreate()
    {
        //Dikkat boot edildiğinde de aşağıdaki kod çalışır
        Intent intent = new Intent(this, SampleService.class);

        intent.putExtra("status", false);
        startService(intent);
        super.onCreate();
    }
}
