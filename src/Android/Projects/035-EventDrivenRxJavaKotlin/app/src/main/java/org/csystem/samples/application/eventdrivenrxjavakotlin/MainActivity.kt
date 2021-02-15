package org.csystem.samples.application.eventdrivenrxjavakotlin

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding4.widget.checkedChanges
import com.jakewharton.rxbinding4.widget.textChanges
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mTextViewResult: TextView
    private lateinit var mEditTextAmount: EditText
    private lateinit var mEditTextRatio: EditText

    private fun onValuesEditTextSubscribe(amount: Double, ratio: Double)
        = "Total:${amount + amount * ratio / 100}".apply {  mTextViewResult.text = this }

    private fun onAmountSubscribe(amount: Double)
    {
        try {
            onValuesEditTextSubscribe(amount, mEditTextRatio.text.toString().toDouble())
        }
        catch (ex: Throwable) {
             "Invalid Ratio Value".apply { mTextViewResult.text = this.toUpperCase(Locale.ROOT) }
        }
    }

    private fun onRatioSubscribe(ratio: Double)
    {
        try {
            onValuesEditTextSubscribe(mEditTextAmount.text.toString().toDouble(), ratio)
        }
        catch (ex: Throwable) {
            "Invalid Amount Value".apply { mTextViewResult.text = this.toUpperCase(Locale.ROOT) }
        }
    }

    private fun initVisibleSwitch()
    {
        val linearLayoutOperations = findViewById<LinearLayout>(R.id.mainActivityLinearLayoutOperations)

        findViewById<Switch>(R.id.mainActivitySwitchVisible).checkedChanges()
            .subscribe{linearLayoutOperations.visibility = if (it) View.VISIBLE else View.GONE}
    }

    private fun initRatioEditText()
    {
        val observable = findViewById<EditText>(R.id.mainActivityEditTextRatio).textChanges()

        observable.filter{it.toString().isNotBlank()}
            .doOnNext{mTextViewResult.text = ""}
            .map {it.toString() }
            .map { it.toDouble() }
            .subscribe{onRatioSubscribe(it)}

        observable
            .filter{it.toString().isBlank()}
            .subscribe{"Invalid Ratio Value".apply {mTextViewResult.text = this}}
    }

    private fun initAmountEditText()
    {
        val observable = findViewById<EditText>(R.id.mainActivityEditTextAmount).textChanges()

        observable.filter{it.toString().isNotBlank()}
            .doOnNext{mTextViewResult.text = ""}
            .map {it.toString() }
            .map { it.toDouble() }
            .subscribe{onAmountSubscribe(it)}

        observable
            .filter{it.toString().isBlank()}
            .subscribe{ "Invalid Amount Value".apply {mTextViewResult.text  = this}}
    }

    private fun initialize()
    {
        mTextViewResult = findViewById(R.id.mainActivityTextViewResult)
        mEditTextAmount = findViewById(R.id.mainActivityEditTextAmount)
        mEditTextRatio = findViewById(R.id.mainActivityEditTextRatio)
        initAmountEditText()
        initRatioEditText()
        initVisibleSwitch()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }
}