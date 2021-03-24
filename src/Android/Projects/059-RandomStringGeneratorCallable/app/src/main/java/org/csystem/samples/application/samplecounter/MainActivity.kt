package org.csystem.samples.application.samplecounter

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import org.csystem.samples.application.samplecounter.databinding.ActivityMainBinding
import org.csystem.util.scheduler.Scheduler
import java.lang.ref.WeakReference
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mHandler = MyHandler(this)
    private var mStop = false
    private lateinit var mRandomGeneratorInitFuture: Future<*>

    @Inject lateinit var threadPool: ExecutorService

    private class MyHandler(mainActivity: MainActivity) : Handler(Looper.myLooper()!!) {
        private val mWeakReference = WeakReference(mainActivity)
        override fun handleMessage(msg: Message)
        {
            val mainActivity = mWeakReference.get()!!
            when (msg.what) {
                0 -> mainActivity.mBinding.mainActivityButtonStart.isEnabled =  true
                1 -> ArrayAdapter(mainActivity, android.R.layout.simple_list_item_1, msg.obj as ArrayList<*>).apply {
                        mainActivity.mBinding.mainActivityListViewTexts.adapter = this
                    }
                2 -> "${mainActivity.mBinding.mainActivityTextViewWaiting.text}.".apply {
                    mainActivity.mBinding.mainActivityTextViewWaiting.text = this
                }
            }
        }
    }

    private fun generateRandomStringEN(n: Int) : String
    {
        val sb = StringBuilder(n)

        fun getChar() = when (Random.nextBoolean()) {
            true -> Random.nextInt('A'.toInt(), ('Z' + 1).toInt()).toChar()
            else -> Random.nextInt('a'.toInt(), ('z' + 1).toInt()).toChar()
        }
        for (i in 1..n)
            sb.append(getChar())

        return sb.toString()
    }

    private fun randomGeneratorThreadCallback(scheduler: Scheduler, count: Int, minLength: Int, maxLength: Int) : ArrayList<String> =
        ArrayList<String>().apply {
            scheduler.schedule { mHandler.sendEmptyMessage(2) }
            for (i in 1..count) {
                this.add(generateRandomStringEN(Random.nextInt(minLength, maxLength)))
                Thread.sleep(Random.nextLong(2000))
            }
        }

    private fun randomGeneratorInitThreadCallback(count: Int, minLength: Int, maxLength: Int)
    {
        val scheduler = Scheduler(1, TimeUnit.SECONDS)
        mBinding.mainActivityTextViewWaiting.text = ""
        val words = threadPool.submit(Callable{randomGeneratorThreadCallback(scheduler, count, minLength, maxLength)}).get()

        scheduler.cancel()
        mHandler.sendMessage(mHandler.obtainMessage(1, words))
        mHandler.sendEmptyMessage(0)
    }

    private fun onStartButtonClicked()
    {
        mBinding.mainActivityButtonStart.isEnabled = false
        val count = mBinding.mainActivityEditTextCount.text.toString().toInt()
        val minLength = mBinding.mainActivityEditTextMinLength.text.toString().toInt()
        val maxLength = mBinding.mainActivityEditTextMaxLength.text.toString().toInt()
        mRandomGeneratorInitFuture = threadPool.submit {randomGeneratorInitThreadCallback(count, minLength, maxLength)}
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
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
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
        if (this::mRandomGeneratorInitFuture.isInitialized)
            mRandomGeneratorInitFuture.cancel(true)

        mBinding.mainActivityButtonStart.isEnabled = true
        mStop = true
        super.onPause()
    }

    override fun onResume()
    {
        initDateTimeTimer()

        super.onResume()
    }
}