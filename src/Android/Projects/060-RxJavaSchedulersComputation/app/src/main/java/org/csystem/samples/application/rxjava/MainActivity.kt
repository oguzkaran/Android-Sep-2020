package org.csystem.samples.application.rxjava

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.csystem.samples.application.rxjava.databinding.ActivityMainBinding
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
                computation scheduler:
                Çalıştığı sistemin processor sayısına göre fixed bir thread havuzundan thread alır.
                Genel olarak hesaplama işlemlerinde (computational tasks) kullanılması uygundur. Örneğin:
                matematiksel algoritmalar ve karmaşık işlemler (complex logic). Processor'e bağlı olduğu
                için çok thread'li uygulamalarda tercih edilmemelidir. Çünkü bazı thread'ler diğer
                thread'leri beklemek zorunda kalabilir. Fixed thread havuzlarını hatırlayınız.
            */

            Observable.range(0, count)
                    .subscribeOn(Schedulers.computation())
                    .map{getResult(sb, milliseconds)}
                    .observeOn(AndroidSchedulers.mainThread())
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