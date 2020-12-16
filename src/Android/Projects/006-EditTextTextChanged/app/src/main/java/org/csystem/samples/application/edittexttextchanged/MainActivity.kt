package org.csystem.samples.application.edittexttextchanged

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextMessage: EditText
    private lateinit var mTextViewMessage: TextView
    private lateinit var mTextViewMessageUpper: TextView

    private inner class MessageEditTextTextWatcher : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
        {
            //TODO("Not yet implemented")
        }

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int)
        {
            text.also { mTextViewMessage.text = it }
        }

        override fun afterTextChanged(p0: Editable?)
        {
            //TODO("Not yet implemented")
        }
    }

    private fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int)
    {
        text.also {
            mTextViewMessage.text = it
            mTextViewMessageUpper.text = it.toString().toUpperCase(Locale.getDefault())
        }
    }

    private fun initEditTextMessage()
    {
        mEditTextMessage = findViewById(R.id.mainActivityEditTextMessage)
        //mEditTextMessage.addTextChangedListener(MessageEditTextTextWatcher())
        mEditTextMessage.addTextChangedListener(onTextChanged = ::onTextChanged)
    }

    private fun initViews()
    {
        initEditTextMessage()
        mTextViewMessage = findViewById(R.id.mainActivityTextViewMessage)
        mTextViewMessageUpper = findViewById(R.id.mainActivityTextViewMessageUpper)
    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }
}