package org.csystem.samples.application.openotheractivity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import org.csystem.samples.application.openotheractivity.IS_OPEN
import org.csystem.samples.application.openotheractivity.NAME
import org.csystem.samples.application.openotheractivity.NUMBER
import org.csystem.samples.application.openotheractivity.R

class SecondActivity : AppCompatActivity() {
    private lateinit var mEditTextName: EditText
    private lateinit var mEditTextNumber: EditText
    private lateinit var mSwitchOpenStatus: Switch

    private fun initNameEditTextView()
    {
        mEditTextName = findViewById(R.id.secondActivityEditTextName)
        mEditTextName.setText(intent.getStringExtra(NAME))
    }

    private fun initNumberEditTextView()
    {
        mEditTextNumber = findViewById(R.id.secondActivityEditTextNumber)
        mEditTextNumber.setText(intent.getLongExtra(NUMBER, -1).toString())
    }

    private fun initOpenStatusSwitchView()
    {
        mSwitchOpenStatus = findViewById(R.id.secondActivitySwitchOpenStatus)
        mSwitchOpenStatus.isChecked = intent.getBooleanExtra(IS_OPEN, false);
    }

    private fun initViews()
    {
        initNameEditTextView()
        initNumberEditTextView()
        initOpenStatusSwitchView()
    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initialize()
    }

    fun onOpenThirdActivityClicked(view: View)
    {
        val intent = Intent(this, ThirdActivity::class.java)

        startActivity(intent)
    }
}