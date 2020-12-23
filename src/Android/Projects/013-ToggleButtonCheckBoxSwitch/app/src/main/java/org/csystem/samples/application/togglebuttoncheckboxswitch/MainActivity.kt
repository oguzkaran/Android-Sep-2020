package org.csystem.samples.application.togglebuttoncheckboxswitch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Switch
import android.widget.Toast
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {
    private lateinit var mToggleButtonConnect: ToggleButton
    private lateinit var mSwitchAllow: Switch
    private lateinit var mCheckBoxAccept: CheckBox

    private fun getToggleButtonConnectMessage(isChecked: Boolean)
                            = if (isChecked) "Cihaz Bağlı" else "Cihaz bağlı değil"
    private fun getSwitchAllowMessage(isChecked: Boolean)
                            = if (isChecked) "İzin verildi" else "İzin verilmedi"

    private fun getCheckedBoxAcceptMessage(isChecked: Boolean)
                            = if (isChecked) "Kabul edildi" else "Kabul edilmedi"

    private fun initViews()
    {
        mToggleButtonConnect = findViewById(R.id.mainActivityToggleButtonConnectDisconnect)
        mToggleButtonConnect.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, getToggleButtonConnectMessage(isChecked), Toast.LENGTH_LONG).show()
        }
        mSwitchAllow = findViewById(R.id.mainActivitySwitchAllow)
        mSwitchAllow.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, getSwitchAllowMessage(isChecked), Toast.LENGTH_LONG).show()
        }
        mCheckBoxAccept = findViewById(R.id.mainActivityCheckBoxAccept)
        mCheckBoxAccept.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, getCheckedBoxAcceptMessage(isChecked), Toast.LENGTH_LONG).show()
        }
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

    fun onOKButtonClicked(view: View)
    {
        val connectionMessage = getToggleButtonConnectMessage(mToggleButtonConnect.isChecked)
        val allowMessage = getSwitchAllowMessage(mSwitchAllow.isChecked)
        val acceptMessage = getCheckedBoxAcceptMessage(mCheckBoxAccept.isChecked)

        "$connectionMessage\n$allowMessage\n$acceptMessage"
            .also {  Toast.makeText(this, it, Toast.LENGTH_LONG).show()}
    }
}