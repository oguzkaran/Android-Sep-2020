package org.csystem.samples.application.generic

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.generic.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySplashBinding

    private fun initBinding()
    {
        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initCountDownTimer()
    {
        object : CountDownTimer(10000, 1000) {
            override fun onTick(remainingMillis: Long)
            {
                mBinding.mainActivityTextViewCounter.text = (remainingMillis / 1000).toString()
            }

            override fun onFinish()
            {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                this@SplashActivity.finish()
            }
        }.start()
    }

    private fun initialize()
    {
        initBinding()
        initCountDownTimer()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}