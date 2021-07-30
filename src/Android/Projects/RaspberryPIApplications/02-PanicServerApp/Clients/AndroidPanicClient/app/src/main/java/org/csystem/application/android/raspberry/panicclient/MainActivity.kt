package org.csystem.application.android.raspberry.panicclient

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.csystem.application.android.raspberry.panicclient.config.HOST
import org.csystem.application.android.raspberry.panicclient.config.PANIC_TCP_PORT
import org.csystem.application.android.raspberry.panicclient.databinding.ActivityMainBinding
import org.csystem.util.net.TcpUtil
import java.net.Socket

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun panicMapCallback(code: Int, message: String, errMessage: String) : String
    {
        Socket(HOST, PANIC_TCP_PORT).use {
            TcpUtil.sendInt(it, code)
            return if (TcpUtil.receiveInt(it) == 1)  message else errMessage
        }
    }

    private fun onPanicButtonsClicked(code: Int, message: String, errMessage: String)
    {
        Observable.just(code)
            .subscribeOn(Schedulers.io())
            .map{panicMapCallback(it, message, errMessage)}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ Toast.makeText(this, it, Toast.LENGTH_LONG).show() },
                { Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()})
    }

    private fun onPanicButtonClicked()
    {
        onPanicButtonsClicked(0, "Hemen yardım gelecek", "Merkezde problem var")
    }

    private fun onNoPanicButtonClicked()
    {
        onPanicButtonsClicked(1, "Panik durumu giderildi", "")
    }

    private fun initViews()
    {
        mBinding.mainAcitivtyButtonPanic.setOnClickListener{onPanicButtonClicked()}
        mBinding.mainAcitivtyButtonNoPanic.setOnClickListener{onNoPanicButtonClicked()}
    }
    private fun initService()
    {
        startService(Intent(this, SendOkService::class.java))
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
        initService()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }
}