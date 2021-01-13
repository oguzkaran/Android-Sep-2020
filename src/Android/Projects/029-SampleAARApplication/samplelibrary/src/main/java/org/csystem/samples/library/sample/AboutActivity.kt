package org.csystem.samples.library.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        Toast.makeText(this, "About Activity", Toast.LENGTH_LONG).show()
    }
    //...
}