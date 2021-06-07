package org.csystem.application.client.textbasecalculator

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.csystem.application.client.textbasecalculator.databinding.ActivityMainBinding
import org.csystem.util.net.TcpUtil
import java.net.Socket
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mCommands: Array<CommandInfo>
    private lateinit var mSocket: Socket

    private fun clearViews()
    {
        val calculatorLayout = mBinding.mainActivityLinearLayoutCalculator
        val count = calculatorLayout.childCount

        for (i in 0 until count) {
            val view = calculatorLayout[i]

            if (view is TextView && view !is Button)
                view.text = ""
            else if (view is Spinner)
                view.setSelection(0)
        }
    }

    private fun configVisibility(isVisible: Boolean)
    {
        val calculatorLayout = mBinding.mainActivityLinearLayoutCalculator
        val count = calculatorLayout.childCount

        if (!isVisible)
            clearViews()

        for (i in 0 until count)
            calculatorLayout[i].visibility = if (isVisible)  View.VISIBLE else View.GONE
    }

    private fun calculateCallBack(cmd: String) : String
    {
        TcpUtil.sendStringViaLength(mSocket, cmd)
        val status = TcpUtil.receiveInt(mSocket)
        val str = TcpUtil.receiveStringViaLength(mSocket)

        return if (status == 1) str else "Fail:$str"
    }

    private fun calculateExceptionCallback(ex: Throwable)
    {
        configVisibility(false)
        mBinding.mainActivityToggleButtonConnection.isChecked = false
        Toast.makeText(this, "${ex.javaClass.name}:${ex.message}", Toast.LENGTH_LONG).show()
    }

    private fun onCalculateButtonClicked()
    {
        try {
            val firstNumber = mBinding.mainActivityEditTextFirstNumber.text.toString().toInt()
            val secondNumber = mBinding.mainActivityEditTextSecondNumber.text.toString().toInt()
            val opText = (mBinding.mainActivitySpinnerOperations.selectedItem as CommandInfo).text
            val cmd = "$opText $firstNumber $secondNumber"

            Observable.just(cmd)
                .subscribeOn(Schedulers.io())
                .map{calculateCallBack(it)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({mBinding.mainActivityTextViewResult.text = it}, {calculateExceptionCallback(it)})

        }
        catch (ex: NumberFormatException) {
            Toast.makeText(this, "Invalid Numbers", Toast.LENGTH_LONG).show()
        }
    }

    private fun onExitButtonClicked()
    {
        Observable.just("quit")
            .subscribeOn(Schedulers.io())
            .takeWhile{ this::mSocket.isInitialized && !mSocket.isClosed }
            .doOnNext{ TcpUtil.sendStringViaLength(mSocket, it) }
            .doOnNext{ mSocket.close() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Toast.makeText(this, "Socket closed", Toast.LENGTH_SHORT).show() },
                { Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show() }
            )
        finish()
    }

    private fun connectCallback(si: ServerInfo) = Socket(si.host, si.port)

    private fun disconnectCallback()
    {
        TcpUtil.sendStringViaLength(mSocket, "quit", StandardCharsets.US_ASCII)
        mSocket.close()
    }

    private fun connectionExceptionCallback(ex: Throwable)
    {
        configVisibility(false)
        mBinding.mainActivityToggleButtonConnection.isChecked = false
        Toast.makeText(this, "${ex.javaClass.name}:${ex.message}", Toast.LENGTH_LONG).show()
    }

    private fun doForChecked(si: ServerInfo)
    {
        Observable.just(si)
            .subscribeOn(Schedulers.io())
            .map { connectCallback(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ mSocket = it; configVisibility(true) }, {connectionExceptionCallback(it)})
    }

    private fun doForUnchecked()
    {
        Observable.just(mSocket)
            .subscribeOn(Schedulers.io())
            .takeWhile{ this::mSocket.isInitialized && !mSocket.isClosed }
            .doOnNext{disconnectCallback()}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ configVisibility(false) }, {})
    }

    private fun onConnectionToggleButtonClicked()
    {
        try {
            val host = mBinding.mainActivityEditTextHost.text.toString()
            val port = mBinding.mainActivityEditTextPort.text.toString().toInt()

            if (mBinding.mainActivityToggleButtonConnection.isChecked)
                doForChecked(ServerInfo(host, port))
            else
                doForUnchecked()
        }
        catch (ex: IllegalArgumentException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
        catch (ignore: NumberFormatException) {
            Toast.makeText(this, "Invalid port number", Toast.LENGTH_LONG).show()
        }
    }

    private fun onClearButtonClicked()
    {
        clearViews()
    }

    private fun initButtons()
    {
        mBinding.mainActivityButtonCalculate.setOnClickListener{onCalculateButtonClicked()}
        mBinding.mainActivityButtonExit.setOnClickListener{onExitButtonClicked()}
        mBinding.mainActivityToggleButtonConnection.setOnClickListener{onConnectionToggleButtonClicked()}
        mBinding.mainActivityButtonClear.setOnClickListener{onClearButtonClicked()}
    }

    private fun initCommands()
    {
        mCommands = arrayOf(
            CommandInfo("ADD", '+', 2),
            CommandInfo("SUB", '-', 2),
            CommandInfo("MUL", '*', 2),
            CommandInfo("DIV", '/', 2),
        )
    }

    private fun initOperationsSpinner()
    {
        initCommands()
        mBinding.mainActivitySpinnerOperations.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mCommands)
    }

    private fun initViews()
    {
        initButtons()
        initOperationsSpinner()
        configVisibility(false)
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

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}