package org.csystem.application.client.light

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.csystem.application.client.light.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun initButtons()
    {
        mBinding.mainActivityButtonLightOnOff.setOnClickListener {startActivity(Intent(this, LightOnOffActivity::class.java))}
        mBinding.mainActivityButtonExit.setOnClickListener {finish()}
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }


    private fun initialize()
    {
        initBinding()
        initButtons()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}