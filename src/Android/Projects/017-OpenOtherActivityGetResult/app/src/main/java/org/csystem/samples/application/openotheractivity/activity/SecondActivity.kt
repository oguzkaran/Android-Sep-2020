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
    private lateinit var mDeviceInfo: DeviceInfo

    private fun setDeviceInfoViews()
    {
        mEditTextName.setText(mDeviceInfo.name)
        mEditTextNumber.setText(mDeviceInfo.number.toString())
        mSwitchOpenStatus.isChecked = mDeviceInfo.isOpen
    }

    private fun setDeviceInfo()
    {
        val name = mEditTextName.text.toString()
        val number = mEditTextNumber.text.toString().toLong()
        val isOpen = mSwitchOpenStatus.isChecked
        mDeviceInfo = DeviceInfo(name, number, isOpen)
    }

    private fun initViews()
    {
        mEditTextName = findViewById(R.id.secondActivityEditTextName)
        mEditTextNumber = findViewById(R.id.secondActivityEditTextNumber)
        mSwitchOpenStatus = findViewById(R.id.secondActivitySwitchOpenStatus)
    }

    private fun initialize()
    {
        initViews()
        mDeviceInfo = intent.getSerializableExtra(DEVICE_INFO) as DeviceInfo
        setDeviceInfoViews()
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

    fun onOKButtonClicked(view: View)
    {
        Intent().also {
            setDeviceInfo()
            it.putExtra(DEVICE_INFO, mDeviceInfo)
            setResult(RESULT_OK, it)
            finish()
        }
    }

    fun onExitButtonClicked(view: View)
    {
        finish()
    }
}