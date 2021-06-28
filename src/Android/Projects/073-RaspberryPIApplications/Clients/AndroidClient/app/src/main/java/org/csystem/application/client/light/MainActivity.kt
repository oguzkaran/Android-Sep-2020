package org.csystem.application.client.light

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.csystem.application.client.light.databinding.ActivityMainBinding
import org.csystem.util.net.TcpUtil
import java.net.Socket

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private data class Info(val host: String, val port: Int)

    private fun getInfo() = Info(resources.getString(R.string.host), resources.getString(R.string.light_info_port).toInt())

    private fun lightInfoCallback(info: Info) : String
    {
        val sb = StringBuilder()

        Socket(info.host, info.port).use {
            val size = TcpUtil.receiveInt(it);

            for (i in 1..size)
                sb.append(TcpUtil.receiveInt(it)).append(", ")
        }

        return sb.substring(0, sb.length - 2)
    }

    private fun onLightInfoButtonClicked()
    {
        try {
            Observable.just(getInfo())
                .subscribeOn(Schedulers.io())
                .map{lightInfoCallback(it)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {setTitle(it)},
                    { Toast.makeText(this, "Light Info: ${it.message}", Toast.LENGTH_LONG).show()})
        }
        catch (ignore: NumberFormatException) {
            Toast.makeText(this, "Invalid values", Toast.LENGTH_LONG).show()
        }
        catch (ex: Throwable) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }

    }

    private fun onLightOnOffButtonClicked()
    {
        startActivity(Intent(this, LightOnOffActivity::class.java).putExtra("title", title))
    }

    private fun initButtons()
    {
        mBinding.mainActivityButtonLightOnOff.setOnClickListener {onLightOnOffButtonClicked()}
        mBinding.mainActivityButtonLightInfo.setOnClickListener{onLightInfoButtonClicked()}
        mBinding.mainActivityButtonExit.setOnClickListener {finish()}
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }


    private fun initialize()
    {
        initBinding()
        initButtons()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}