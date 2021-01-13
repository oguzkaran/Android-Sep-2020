package org.csystem.android.util.application;

import android.app.Application;

public class App extends Application {
    private static App ms_instance;
    @Override
    public void onCreate()
    {
        ms_instance = this;
        super.onCreate();
    }

    public static App getInstance()
    {
        return ms_instance;
    }
}
