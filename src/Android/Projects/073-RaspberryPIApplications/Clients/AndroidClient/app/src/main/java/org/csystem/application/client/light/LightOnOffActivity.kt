package org.csystem.application.client.light

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.csystem.application.client.light.databinding.ActivityLightOnOffBinding
import org.csystem.util.net.TcpUtil
import java.lang.NumberFormatException
import java.net.Socket
import java.util.*

class LightOnOffActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLightOnOffBinding

    private data class Info(val host: String, val port: Int, val ioNo: Int, val count: Int,
                            val millisecond: Long)

    private fun getInfo() : Info
    {
        val host = mBinding.lightOnOffActivityEditTextHost.text.toString()
        val port = mBinding.lightOnOffActivityEditTextPort.text.toString().toInt()
        val ioNo = mBinding.lightOnOffActivityEditTextIoNo.text.toString().toInt()
        val count = mBinding.lightOnOffActivityEditTextCount.text.toString().toInt()
        val millisecond = mBinding.lightOnOffActivityEditTextMillisecond.text.toString().toLong()

        return Info(host, port, ioNo, count, millisecond)
    }

    private fun lightOnOffServerProc(info: Info) : Byte
    {
        var result: Byte = 0

        Socket(info.host, info.port).use {
            TcpUtil.sendByte(it, 0)
            if (TcpUtil.receiveByte(it).toInt() == 1) {
                TcpUtil.sendInt(it, info.ioNo)
                TcpUtil.sendInt(it, info.count)
                TcpUtil.sendLong(it, info.millisecond)

                result = TcpUtil.receiveByte(it)
            }
        }

        return result
    }

    private fun onButtonLightOnOff()
    {
        try {
            Observable.just(getInfo())
                .subscribeOn(Schedulers.io())
                .map{lightOnOffServerProc(it)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {mBinding.lightOnOffActivityTextViewResult.text = if (it.toInt() == 1) "Success" else "Fail"},
                    {Toast.makeText(this, it.javaClass.name, Toast.LENGTH_LONG).show()})
        }
        catch (ignore: NumberFormatException) {
            Toast.makeText(this, "Invalid values", Toast.LENGTH_LONG).show()
        }
        catch (ex: Throwable) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun initButtons()
    {
        mBinding.lightOnOffActivityButtonLightOnOff.setOnClickListener{onButtonLightOnOff()}
    }

    private fun initViews()
    {
        initButtons()
    }

    private fun initBinding()
    {
        mBinding = ActivityLightOnOffBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
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
}