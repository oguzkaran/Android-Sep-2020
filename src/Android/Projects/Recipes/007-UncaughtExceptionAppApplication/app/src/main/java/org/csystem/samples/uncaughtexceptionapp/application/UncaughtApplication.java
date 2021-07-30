package org.csystem.samples.uncaughtexceptionapp.application;

import android.util.Log;

import org.csystem.android.application.App;
import org.csystem.android.application.ApplicationUtil;
import org.csystem.samples.uncaughtexceptionapp.MainActivity;

public class UncaughtApplication extends App {
    private void doWorkForAction()
    {
        Log.i("test", "CRASH");
        System.out.println("CRASH");
    }

    @Override
    public void onCreate()
    {
        ApplicationUtil.setUncaughtExceptionHandler(getApplicationContext(), MainActivity.class, this::doWorkForAction);
        super.onCreate();
    }
}
