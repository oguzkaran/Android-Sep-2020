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

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextName: EditText
    private lateinit var mEditTextNumber: EditText
    private lateinit var mSwitchOpenStatus: Switch

    private fun initViews()
    {
        mEditTextName = findViewById(R.id.mainActivityEditTextName)
        mEditTextNumber = findViewById(R.id.mainActivityEditTextNumber)
        mSwitchOpenStatus = findViewById(R.id.mainActivitySwitchOpenStatus)
    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }
    //...

    fun onOpenSecondActivityClicked(view: View)
    {
        val name = mEditTextName.text.toString()
        val number = mEditTextNumber.text.toString().toLong()
        val isOpen = mSwitchOpenStatus.isChecked
        val intent = Intent(this, SecondActivity::class.java)

        intent.putExtra(NAME, name)
        intent.putExtra(NUMBER, number)
        intent.putExtra(IS_OPEN, isOpen)

        startActivity(intent)
    }
}