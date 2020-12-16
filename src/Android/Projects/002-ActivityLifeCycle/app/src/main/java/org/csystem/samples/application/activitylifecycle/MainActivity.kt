package org.csystem.samples.application.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private fun initViews()
    {
        //...
    }

    private fun initCheckCreate(bundle: Bundle?)
    {
        val message = "onCreate:${if (bundle == null) "Manuel yaratıldı" else "Otomatik yaratıldı"}"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private fun initialize(bundle: Bundle?)
    {
        initCheckCreate(bundle)
        initViews()
        //..
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize(savedInstanceState)
    }

    override fun onStart()
    {
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
        super.onStart()
    }

    override fun onResume()
    {
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        super.onResume()
    }

    override fun onPause()
    {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause()
    }

    override fun onStop()
    {
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop()
    }

    override fun onRestart()
    {
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
        super.onRestart()
    }

    override fun onDestroy()
    {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy()
    }
}