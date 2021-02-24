package org.csystem.samples.application.sharedpreferences

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.csystem.samples.application.sharedpreferences.databinding.ActivitySecondBinding

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
        val sharedPrefs = getSharedPreferences("activity_settings", MODE_PRIVATE)
        mBinding.secondActivityConstraintLayoutMain.setBackgroundColor(sharedPrefs.getInt("bgcolor", Color.GRAY))
        super.onResume()
    }
}