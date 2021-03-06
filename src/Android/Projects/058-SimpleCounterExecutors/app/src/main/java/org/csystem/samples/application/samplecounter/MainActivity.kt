package org.csystem.samples.application.samplecounter

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import org.csystem.samples.application.samplecounter.application.SampleCounterApplication
import org.csystem.samples.application.samplecounter.databinding.ActivityMainBinding
import java.lang.ref.WeakReference
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mHandler = MyHandler(this)
    private var mStop = false
    private lateinit var mCounterFuture: Future<*>
    private var mCounter = 1L;

    @Inject lateinit var threadPool: ExecutorService

    private class MyHandler(mainActivity: MainActivity) : Handler(Looper.myLooper()!!) {
        private val mWeakReference = WeakReference(mainActivity)

        private fun handleCounter(mainActivity: MainActivity, msg: Message)
        {
            val counter = msg.obj.toString().toLong()

            mainActivity.mBinding.mainActivityTextViewCounter.text = counter.toString()
            Toast.makeText(mainActivity, counter.toString(), Toast.LENGTH_SHORT).show()
        }

        override fun handleMessage(msg: Message)
        {
            val mainActivity = mWeakReference.get()!!

            when (msg.what) {
                1 -> handleCounter(mainActivity, msg)
                2 -> Toast.makeText(mainActivity, "Sayaç sonlandırıldı", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onStartButtonClicked()
    {
        mBinding.mainActivityButtonStart.isEnabled = false

        mCounterFuture = threadPool.submit {
            try {
                while (true) {
                    mHandler.sendMessage(mHandler.obtainMessage(1, mCounter++))
                    Thread.sleep(1000)
                }
            }
            catch (ignore: InterruptedException) {
                mHandler.sendEmptyMessage(2)
            }
        }
    }

    private fun dateTimeTimerCancelledCallback()
    {
        Toast.makeText(this, "Timer sonlandırıldı", Toast.LENGTH_LONG).show()
    }

    private fun dateTimeTimerCallback()
    {
        val dateTimeTimerStr = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss").format(LocalDateTime.now())
        title = dateTimeTimerStr
    }

    private fun initStartButton()
    {
        mBinding.mainActivityButtonStart.setOnClickListener { onStartButtonClicked() }
    }

    private fun initDateTimeTimer()
    {
        mStop = false
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .takeUntil{mStop}
                .subscribe({dateTimeTimerCallback()}, {title = it.message}, {dateTimeTimerCancelledCallback()})
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initViews()
    {
        initStartButton()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //Bu activity sadece PORTRAIT görünümde çalışır
    }

    private fun initialize()
    {
        initBinding()
        initViews()
    }



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onPause()
    {
        if (this::mCounterFuture.isInitialized)
            mCounterFuture.cancel(true)

        mStop = true
        super.onPause()
    }

    override fun onResume()
    {
        initDateTimeTimer()
        if (this::mCounterFuture.isInitialized)
            onStartButtonClicked()

        super.onResume()
    }
}