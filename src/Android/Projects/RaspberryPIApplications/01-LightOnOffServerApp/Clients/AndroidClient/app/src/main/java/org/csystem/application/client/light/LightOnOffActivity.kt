package org.csystem.application.client.light

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.csystem.application.client.light.databinding.ActivityLightOnOffBinding
import org.csystem.util.net.TcpUtil
import java.lang.NumberFormatException
import java.net.Socket

class LightOnOffActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLightOnOffBinding

    private data class Info(val host: String, val port: Int, val ioNo: Int, val count: Int = 0,
                            val millisecond: Long = 0L)

    private fun getInfo() : Info
    {
        val host = mBinding.lightOnOffActivityEditTextHost.text.toString()
        val port = mBinding.lightOnOffActivityEditTextPort.text.toString().toInt()
        val ioNo = mBinding.lightOnOffActivityEditTextIoNo.text.toString().toInt()
        val count = mBinding.lightOnOffActivityEditTextCount.text.toString().toInt()
        val millisecond = mBinding.lightOnOffActivityEditTextMillisecond.text.toString().toLong()

        return Info(host, port, ioNo, count, millisecond)
    }

    private fun getInfoWithIo() : Info
    {
        val host = mBinding.lightOnOffActivityEditTextHost.text.toString()
        val port = mBinding.lightOnOffActivityEditTextPort.text.toString().toInt()
        val ioNo = mBinding.lightOnOffActivityEditTextIoNo.text.toString().toInt()

        return Info(host, port, ioNo)
    }

    private fun lightOnOffCallback(info: Info) : Int
    {
        var result = 0

        Socket(info.host, info.port).use {
            TcpUtil.sendInt(it, 0)
            if (TcpUtil.receiveInt(it) == 1) {
                TcpUtil.sendInt(it, info.ioNo)
                TcpUtil.sendInt(it, info.count)
                TcpUtil.sendLong(it, info.millisecond)

                result = TcpUtil.receiveInt(it)
                Log.d("result", result.toString())
            }
        }

        return result
    }

    private fun lightOnOff(info: Info, code: Int) : Int
    {
        var result = 0

        Socket(info.host, info.port).use {
            TcpUtil.sendInt(it, code)
            if (TcpUtil.receiveInt(it) == 1) {
                TcpUtil.sendInt(it, info.ioNo)
                result = TcpUtil.receiveInt(it)
            }
        }

        return result
    }

    private fun lightOnCallback(info: Info)  = lightOnOff(info, 1)
    private fun lightOffCallback(info: Info)  = lightOnOff(info, -1)

    private fun onLightOnOffButtonClicked()
    {
        try {
            Observable.just(getInfo())
                .subscribeOn(Schedulers.io())
                .map{lightOnOffCallback(it)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {mBinding.lightOnOffActivityTextViewResult.text = if (it == 1) "Success" else "Fail"},
                    {Toast.makeText(this, "Light OnOff: ${it.message}", Toast.LENGTH_LONG).show()})
        }
        catch (ignore: NumberFormatException) {
            Toast.makeText(this, "Invalid values", Toast.LENGTH_LONG).show()
        }
        catch (ex: Throwable) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }


    private fun onLightOnButtonClicked()
    {
        try {
            Observable.just(getInfoWithIo())
                .subscribeOn(Schedulers.io())
                .map{lightOnCallback(it)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {mBinding.lightOnOffActivityTextViewResult.text = if (it == 1) "Success" else "Fail"},
                    {Toast.makeText(this, "Light On: ${it.message}", Toast.LENGTH_LONG).show()})
        }
        catch (ignore: NumberFormatException) {
            Toast.makeText(this, "Invalid values", Toast.LENGTH_LONG).show()
        }
        catch (ex: Throwable) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun onLightOffButtonClicked()
    {
        try {
            Observable.just(getInfoWithIo())
                .subscribeOn(Schedulers.io())
                .map{lightOffCallback(it)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {mBinding.lightOnOffActivityTextViewResult.text = if (it == 1) "Success" else "Fail"},
                    {Toast.makeText(this, "Light Off: ${it.message}", Toast.LENGTH_LONG).show()})
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
        mBinding.lightOnOffActivityButtonLightOnOff.setOnClickListener{onLightOnOffButtonClicked()}
        mBinding.lightOnOffActivityButtonLightOn.setOnClickListener{onLightOnButtonClicked()}
        mBinding.lightOnOffActivityButtonLightOff.setOnClickListener{onLightOffButtonClicked()}
    }

    private fun initViews()
    {
        title = intent.getStringExtra("title")
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