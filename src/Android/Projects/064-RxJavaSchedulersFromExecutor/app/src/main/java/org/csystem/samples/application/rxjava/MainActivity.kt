package org.csystem.samples.application.rxjava

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.csystem.samples.application.rxjava.databinding.ActivityMainBinding
import java.util.concurrent.Executors
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun getResult(sb: StringBuilder, milliseconds: Long) : String
    {
        val value = Random.nextInt(100)

        sb.append(value).append('-')
        Thread.sleep(milliseconds)

        return sb.substring(0, sb.length - 1)
    }

    private fun onGenerateButtonClicked()
    {
        try {
            val count = mBinding.mainActivityEditTextCount.text.toString().toInt()
            val milliseconds = mBinding.mainActivityEditTextSleepMilliseconds.text.toString().toLong()
            val textViewNumber = mBinding.mainActivityTextViewNumber
            val sb = StringBuilder()

            /*
                from scheduler:
                Bu scheduler asında bir Executor'dan scheduler oluşturmak için kullanılır. Örneğin, bir
                thread havuzundan ya da bir "Single Thread Executor"'dan scheduler elde edilebilir
            */

            //Örnekte her işlem için bir "Single Thread Executor" yaratılmış ve RxJava scheduler bundan elde edilmiştir
            val executorService = Executors.newSingleThreadExecutor()

            Observable.range(0, count)
                    .subscribeOn(Schedulers.from(executorService))
                    .map{getResult(sb, milliseconds)}
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally{executorService.shutdown()}
                    .subscribe({textViewNumber.text = it}, {}, {mBinding.mainActivityButtonGenerate.isEnabled = true})

            mBinding.mainActivityButtonGenerate.isEnabled = false
        }
        catch (ex: NumberFormatException) {
            Toast.makeText(this, "Geçersiz değerler", Toast.LENGTH_LONG).show()
        }
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
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}