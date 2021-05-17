package org.csystem.samples.application.reversepalindrome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.csystem.samples.application.reversepalindrome.databinding.ActivityMainBinding
import java.io.*
import java.net.Socket
import java.util.*

const val REVERSE_PORT = 5054
const val PALINDROME_PORT = 5055

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun getResult(text:String, host: String, port: Int) : String
    {
        Socket(host, port).use {
            val bufferedWriter = BufferedWriter(OutputStreamWriter(it.getOutputStream()))

            bufferedWriter.write(text + "\r\n")
            bufferedWriter.flush()

            if (port == REVERSE_PORT)
                return BufferedReader(InputStreamReader(it.getInputStream())).readLine();

            val dataInputStream = DataInputStream(it.getInputStream())

            return if (dataInputStream.readBoolean()) "Palindrom" else "Palindrom değil"
        }
    }

    private fun onOKButtonClicked()
    {
        val host = mBinding.mainActivityEditTextHost.text.toString()
        val port = if (mBinding.mainActivityRadioGroupServers.checkedRadioButtonId == R.id.mainActivityRadioButtonReverse) REVERSE_PORT else PALINDROME_PORT

        Observable.just(mBinding.mainActivityEditTextText.text.toString())
            .subscribeOn(Schedulers.io())
            .map { getResult(it, host, port) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ mBinding.mainActivityTextViewResult.text = it }, { Toast.makeText(this, "${it.javaClass.name}:${it.message}", Toast.LENGTH_LONG).show()})
    }

    private fun initButtons()
    {
        mBinding.mainActivityButtonOK.setOnClickListener { onOKButtonClicked() }
    }

    private fun initViews()
    {
        initButtons()
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
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }
}