package org.csystem.samples.application.reversepalindrome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.csystem.samples.application.reversepalindrome.databinding.ActivityMainBinding
import org.csystem.util.net.TcpUtil
import java.io.*
import java.net.Socket
import java.util.*

const val UPPER_PORT = 5054

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun getResult(text:String, host: String, port: Int) : String
    {
        Socket(host, port).use {
            TcpUtil.sendStringViaLength(it, text)

            if (text == "quit")
                return ""

            val result = TcpUtil.receiveStringViaLength(it)

            TcpUtil.sendStringViaLength(it, "quit")

            return result
        }
    }

    private fun onOKButtonClicked()
    {
        try {
            val host = mBinding.mainActivityEditTextHost.text.toString()
            val port = mBinding.mainActivityEditTextPort.text.toString().toInt()

            Observable.just(mBinding.mainActivityEditTextText.text.toString())
                .subscribeOn(Schedulers.io())
                .map { getResult(it, host, port) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ mBinding.mainActivityTextViewResult.text = it }, { Toast.makeText(this, "${it.javaClass.name}:${it.message}", Toast.LENGTH_LONG).show()})
        }
        catch (ex: NumberFormatException) {
            Toast.makeText(this, "Invalid port", Toast.LENGTH_LONG).show()
        }
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