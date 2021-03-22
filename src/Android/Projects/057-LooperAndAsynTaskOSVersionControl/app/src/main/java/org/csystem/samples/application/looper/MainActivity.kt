package org.csystem.samples.application.looper

import android.os.*
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.looper.databinding.ActivityMainBinding
import java.lang.ref.WeakReference
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mRandomGeneratorHandler: RandomGeneratorHandler
    private lateinit var mMainHandler: MainHandler

    private data class ParamInfo(val count: Int, val milliseconds: Long)

    private class RandomGeneratorTask(mainActivity: MainActivity) : AsyncTask<ParamInfo, Int, Unit>() {
        private val mWeakReference = WeakReference(mainActivity)

        override fun doInBackground(vararg pis: ParamInfo?): Unit
        {
            val pi = pis[0]!!
            val count = pi.count
            val milliseconds = pi.milliseconds

            for (i in 1..count) {
                val value = Random.nextInt(100)
                publishProgress(value)
                Thread.sleep(milliseconds)
            }
        }

        override fun onProgressUpdate(vararg values: Int?)
        {
            val mainActivity = mWeakReference.get()!!
            val textViewNumber = mainActivity.mBinding.mainActivityTextViewNumber
            "${textViewNumber.text}-${values[0]}".apply { textViewNumber.text = this }
            Toast.makeText(mainActivity, "${values[0]}", Toast.LENGTH_SHORT).show()
        }

        override fun onPostExecute(result: Unit?)
        {
            val mainActivity = mWeakReference.get()!!

            mainActivity.mBinding.mainActivityButtonGenerate.isEnabled = true
        }
    }

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

    private fun onGenerateButtonClickedBeforeApiLevel30(count: Int, milliseconds: Long)
    {
        RandomGeneratorTask(this).execute(ParamInfo(count, milliseconds))
    }

    private fun onGenerateButtonClickedApiLevel30(count: Int, milliseconds: Long)
    {
        val message = mRandomGeneratorHandler.obtainMessage(0, RandomGeneratorThreadParam(count, milliseconds))

        mBinding.mainActivityButtonGenerate.isEnabled = false
        mRandomGeneratorHandler.sendMessage(message)
    }

    private fun onGenerateButtonClicked()
    {
        try {
            val count = mBinding.mainActivityEditTextCount.text.toString().toInt()
            val milliseconds = mBinding.mainActivityEditTextSleepMilliseconds.text.toString().toLong()

            if (Build.VERSION.SDK_INT >= 30)
                onGenerateButtonClickedApiLevel30(count, milliseconds)
            else
                onGenerateButtonClickedBeforeApiLevel30(count, milliseconds)

            mBinding.mainActivityButtonGenerate.isEnabled = false
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

    private fun initViews()
    {
        mBinding.mainActivityTextViewNumber.text =
                if (Build.VERSION.SDK_INT < 30) "AsyncTask:Numbers-" else "Numbers-"
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
        initViews()
        initGenerateButton()
        initLooper()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}