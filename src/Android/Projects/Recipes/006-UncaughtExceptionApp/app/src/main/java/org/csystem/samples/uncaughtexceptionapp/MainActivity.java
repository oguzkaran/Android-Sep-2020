package org.csystem.samples.uncaughtexceptionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.csystem.android.application.ApplicationUtil;

public class MainActivity extends AppCompatActivity {

    private void initForCrash()
    {
        if (getIntent().getBooleanExtra("crash", false))
            Toast.makeText(this, "Activity started after crash", Toast.LENGTH_LONG).show();
    }

    private void init()
    {
        ApplicationUtil.setUncaughtExceptionHandler(this, MainActivity.class);
        this.initForCrash();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    public void onOKButtonClicked(View view)
    {
        throw new RuntimeException();
    }
}
