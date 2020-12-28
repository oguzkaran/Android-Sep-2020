package org.csystem.samples.application.openotheractivity.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.openotheractivity.DEVICE_INFO
import org.csystem.samples.application.openotheractivity.R
import org.csystem.samples.application.openotheractivity.entity.DeviceInfo

class SecondActivity : AppCompatActivity() {
    private lateinit var mEditTextName: EditText
    private lateinit var mEditTextNumber: EditText
    private lateinit var mSwitchOpenStatus: Switch

    private fun initNameEditTextView(deviceInfo: DeviceInfo)
    {
        mEditTextName = findViewById(R.id.secondActivityEditTextName)
        mEditTextName.setText(deviceInfo.name)
    }

    private fun initNumberEditTextView(deviceInfo: DeviceInfo)
    {
        mEditTextNumber = findViewById(R.id.secondActivityEditTextNumber)
        mEditTextNumber.setText(deviceInfo.number.toString())
    }

    private fun initOpenStatusSwitchView(deviceInfo: DeviceInfo)
    {
        mSwitchOpenStatus = findViewById(R.id.secondActivitySwitchOpenStatus)
        mSwitchOpenStatus.isChecked = deviceInfo.isOpen
    }

    private fun initViews()
    {
        val deviceInfo = intent.getSerializableExtra(DEVICE_INFO) as DeviceInfo

        initNameEditTextView(deviceInfo)
        initNumberEditTextView(deviceInfo)
        initOpenStatusSwitchView(deviceInfo)
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