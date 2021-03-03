package org.csystem.samples.application.samplecounterdonotdo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.samplecounterdonotdo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private var mCounter = 0

    private fun onStartButtonClicked()
    {
        while (true) {
            mBinding.mainActivityTextViewCounter.text = (++mCounter).toString()
            Thread.sleep(1000)
        }
    }

    private fun initStartButton()
    {
        mBinding.mainActivityButtonStart.setOnClickListener { onStartButtonClicked() }
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }


    private fun initialize()
    {
        initBinding()
        initStartButton()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

}