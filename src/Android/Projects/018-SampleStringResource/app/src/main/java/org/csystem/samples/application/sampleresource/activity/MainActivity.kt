package org.csystem.samples.application.sampleresource.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.sampleresource.DEVICE_INFO
import org.csystem.samples.application.sampleresource.R
import org.csystem.samples.application.sampleresource.entity.DeviceInfo

class MainActivity : AppCompatActivity() {
    companion object {
        private const val SECOND_ACTIVITY_REQUEST_CODE = 1
        private const val THIRD_ACTIVITY_REQUEST_CODE = 2
    }
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

    private fun secondActivityRequestProc(resultCode: Int, data: Intent?)
    {
        if (resultCode == RESULT_OK)
            (data?.getSerializableExtra(DEVICE_INFO) as DeviceInfo).also { mDeviceInfo = it; setDeviceInfoViews() }
        else
            Toast.makeText(this, R.string.second_activity_cancelled_message, Toast.LENGTH_SHORT).show()
    }

    private fun thirdActivityRequestProc(resultCode: Int)
    {
        if (resultCode == RESULT_OK)
            setTitle(R.string.thirdactivity_ok_message)
        else {
            Toast.makeText(this, R.string.third_activity_cancelled_message, Toast.LENGTH_SHORT).show()
            setTitle(R.string.thirdactivity_cancelled_title_message)
        }
    }

    private fun initViews()
    {
        mEditTextName = findViewById(R.id.mainActivityEditTextName)
        mEditTextNumber = findViewById(R.id.mainActivityEditTextNumber)
        mSwitchOpenStatus = findViewById(R.id.mainActivitySwitchOpenStatus)
    }

    private fun initialize()
    {
        initViews()
        val appName = resources.getString(R.string.mainactivity_button_open_second_activity)

        Toast.makeText(this, appName, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }
    //...

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        when (requestCode) {
            SECOND_ACTIVITY_REQUEST_CODE -> secondActivityRequestProc(resultCode, data)
            THIRD_ACTIVITY_REQUEST_CODE -> thirdActivityRequestProc(resultCode)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun onOpenSecondActivityButtonClicked(view: View)
    {
        Intent(this, SecondActivity::class.java).also {
            setDeviceInfo()
            it.putExtra(DEVICE_INFO, mDeviceInfo)
            startActivityForResult(it, SECOND_ACTIVITY_REQUEST_CODE)
        }
    }

    fun onOpenThirdActivityButtonClicked(view: View)
    {
        Intent(this, ThirdActivity::class.java).also {
            startActivityForResult(it, THIRD_ACTIVITY_REQUEST_CODE)
        }
    }
    fun onExitButtonClicked(view: View)
    {
        finish()
    }
}