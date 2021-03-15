package org.csystem.samples.application.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.looper.databinding.ActivityMainBinding
import java.lang.ref.WeakReference
import java.util.stream.Collectors
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mRandomGeneratorHandler: RandomGeneratorHandler
    private lateinit var mMainHandler: MainHandler

    private class RandomGeneratorHandler(mainActivity : MainActivity) : Handler(Looper.myLooper()!!) {
        private val mWeakReference = WeakReference(mainActivity)

        override fun handleMessage(msg: Message)
        {
            val param = msg.obj as RandomGeneratorThreadParam
            val mainActivity = mWeakReference.get()!!
            val mainHandler = mainActivity.mMainHandler

            for (i in 1..param.count) {
                val value = Random.nextInt(100)
                mainHandler.sendMessage(mainHandler.obtainMessage(0, value))
                mainHandler.sendMessage(mainHandler.obtainMessage(1, "Value:${value}"))
                Thread.sleep(param.milliseconds)
            }
            mainHandler.sendEmptyMessage(2)
        }
    }

    private class MainHandler(mainActivity : MainActivity) : Handler(Looper.myLooper()!!) {
        private val mWeakReference = WeakReference(mainActivity)
        override fun handleMessage(msg: Message)
        {
            val mainActivity = mWeakReference.get()!!
            val textViewNumber = mainActivity.mBinding.mainActivityTextViewNumber

            when (msg.what) {
                0 -> "${textViewNumber.text}-${msg.obj}".apply { textViewNumber.text = this }
                1 -> Toast.makeText(mainActivity, msg.obj.toString(), Toast.LENGTH_SHORT).show()
                2 -> mainActivity.mBinding.mainActivityButtonGenerate.isEnabled = true
            }
        }
    }

    private data class RandomGeneratorThreadParam(val count: Int, val milliseconds: Long)

    private fun onGenerateButtonClicked()
    {
        try {
            val count = mBinding.mainActivityEditTextCount.text.toString().toInt()
            val milliseconds = mBinding.mainActivityEditTextSleepMilliseconds.text.toString().toLong()
            val message = mRandomGeneratorHandler.obtainMessage(0, RandomGeneratorThreadParam(count, milliseconds))

            mBinding.mainActivityButtonGenerate.isEnabled = false
            mRandomGeneratorHandler.sendMessage(message)
        }
        catch (ex: NumberFormatException) {
            Toast.makeText(this, "Geçersiz değerler", Toast.LENGTH_LONG).show()
        }
    }

    @WorkerThread
    private fun runLooper()
    {
        Looper.prepare()

        mRandomGeneratorHandler = RandomGeneratorHandler(this)

        Looper.loop()
    }

    private fun initLooper()
    {
        mMainHandler = MainHandler(this)
        Thread{runLooper()}.start()
    }

    private fun initGenerateButton()
    {
        mBinding.mainActivityButtonGenerate.setOnClickListener { onGenerateButtonClicked() };
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
        initGenerateButton()
        initLooper()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}