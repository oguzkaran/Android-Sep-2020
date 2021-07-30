package org.csystem.samples.simplebootapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Aşağıdaki kod boot işleminde çalışmaz
        Intent intent = new Intent(this, SampleService.class);

        intent.putExtra("status", false);
        startService(intent);
        finish();
    }
}
