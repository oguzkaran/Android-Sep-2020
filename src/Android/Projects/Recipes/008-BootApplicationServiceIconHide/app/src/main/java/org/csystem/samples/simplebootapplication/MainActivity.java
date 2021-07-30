package org.csystem.samples.simplebootapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import org.csystem.android.application.ApplicationUtil;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, SampleService.class));
        ApplicationUtil.disableLauncher(this, MainActivity.class);
        finish();
    }
}
