package org.csystem.samples.application.samplecounterdonotdo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.samplecounterdonotdo.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private var mCounter = 0
    private val mHandler = Handler(Looper.myLooper()!!)
    private lateinit var mDateTimeTimer: Timer

    private fun onStartButtonClicked()
    {
        mBinding.mainActivityButtonStart.isEnabled = false
        
        Thread { //Dikkat activity yok edildiğinde bu thread'in de sonlandırılması gerekir. Henüz yapmadık
            while (true) {
                mHandler.post { mBinding.mainActivityTextViewCounter.text = (++mCounter).toString()
                    Toast.makeText(this, mCounter.toString(), Toast.LENGTH_SHORT).show() }
                Thread.sleep(1000)
            }
        }.start()
    }

    private fun initStartButton()
    {
        mBinding.mainActivityButtonStart.setOnClickListener { onStartButtonClicked() }
    }

    private fun initDateTimeTimer()
    {
        val task = object: TimerTask() {
            override fun run()
            {
                val dateTimeTimerStr = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss").format(LocalDateTime.now())
                mHandler.post { setTitle(dateTimeTimerStr) }
            }
        }
        mDateTimeTimer = Timer().apply { scheduleAtFixedRate(task, 0, 1000) } //Bu timer'ın da activity yok edildiğinde durdurulması gerekir
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
        initDateTimeTimer()
        initStartButton()

    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

}