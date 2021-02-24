package org.csystem.samples.application.sharedpreferences

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.csystem.samples.application.sharedpreferences.databinding.ActivitySecondBinding
import org.csystem.samples.application.sharedpreferences.global.ACTIVITY_SETTINGS
import org.csystem.samples.application.sharedpreferences.global.BACKGROUND_COLOR

class SecondActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySecondBinding

    private fun initBinding()
    {
        mBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onResume()
    {
        val sharedPrefs = getSharedPreferences(ACTIVITY_SETTINGS, MODE_PRIVATE)
        mBinding.secondActivityConstraintLayoutMain.setBackgroundColor(sharedPrefs.getInt(BACKGROUND_COLOR, Color.GRAY))
        super.onResume()
    }
}