package org.csystem.samples.application.sampleresource.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.sampleresource.R

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