package org.csystem.apps.openactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartActivityButtonClicked(View view)
    {
        Intent intent = new Intent("org.csystem.libs.activitylib.action.MY_ACTION");

        startActivity(intent);
    }
}