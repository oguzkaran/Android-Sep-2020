package org.csystem.application.backgroundservice

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.csystem.application.backgroundservice.databinding.ActivityMainBinding
import org.csystem.application.backgroundservice.global.Global
import java.io.DataInputStream
import java.io.EOFException

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun onStartServiceButtonClicked()
    {
        try {
            Intent(this, RandomNumberGeneratorService::class.java).apply {
                putExtra("filename", mBinding.mainActivityEditFilename.text.toString())
                putExtra("count", mBinding.mainActivityEditTextCount.text.toString().toInt())
                putExtra("min", mBinding.mainActivityEditTextMin.text.toString().toInt())
                putExtra("max", mBinding.mainActivityEditTextMax.text.toString().toInt())

                synchronized(applicationContext) {
                    if (Global.isServiceRunning) {
                        Toast.makeText(this@MainActivity, "Service is running", Toast.LENGTH_LONG).show()
                        return
                    }
                    startService(this)
                    Global.isServiceRunning = true;
                    finish()
                }
            }
        }
        catch (ignore: NumberFormatException) {
            Toast.makeText(this, "Invalid values", Toast.LENGTH_LONG).show()
        }
        catch (ex: Throwable) {
            Toast.makeText(this, "Unknown problem:${ex.javaClass.name}:${ex.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun getNumbersMapCallback(filename: String) : String
    {
        val sb = StringBuilder()

       openFileInput(filename).use {
            val dis = DataInputStream(it)
            try {
                while (true)
                    sb.append(dis.readInt()).append('-')
            }
            catch (ignore: EOFException) {

            }
       }

        return sb.substring(0, sb.length - 1)
    }

    private fun onGetNumbersButtonClicked()
    {
        Observable.just(mBinding.mainActivityEditFilename.text.toString())
            .subscribeOn(Schedulers.io())
            .map{getNumbersMapCallback(it)}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ mBinding.mainActivityTextViewNumbers.text = it }, {Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()})
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initButtons()
    {
        mBinding.mainActivityButtonStartService.setOnClickListener{onStartServiceButtonClicked()}
        mBinding.mainActivityButtonGetNumbers.setOnClickListener{onGetNumbersButtonClicked()}
    }

    private fun initViews()
    {
        initButtons()
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