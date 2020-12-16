package org.csystem.application.firstapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val now = LocalTime.now()

        Toast.makeText(this, DateTimeFormatter.ofPattern("hh:mm:ss").format(now), Toast.LENGTH_LONG).show()
    }
}