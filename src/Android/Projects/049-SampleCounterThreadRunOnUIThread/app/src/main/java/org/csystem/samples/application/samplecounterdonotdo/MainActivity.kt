package org.csystem.samples.application.samplecounterdonotdo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.samplecounterdonotdo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private var mCounter = 0

    private fun onStartButtonClicked()
    {
        mBinding.mainActivityButtonStart.isEnabled = false
        
        Thread { //Dikkat activity yok edildiğinde bu thread'in de sonlandırılması gerekir. Henüz yapmadık
            while (true) {
                runOnUiThread{mBinding.mainActivityTextViewCounter.text = (++mCounter).toString()}
                Thread.sleep(1000)
            }
        }.start()
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