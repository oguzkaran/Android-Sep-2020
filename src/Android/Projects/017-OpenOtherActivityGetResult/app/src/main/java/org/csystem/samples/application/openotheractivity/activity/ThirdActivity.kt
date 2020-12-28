package org.csystem.samples.application.openotheractivity.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.openotheractivity.R

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }

    fun onOKButtonClicked(view: View)
    {
        run {
            setResult(RESULT_OK)
            finish()
        }
    }
    fun onExitButtonClicked(view: View)
    {
        finish()
    }
}