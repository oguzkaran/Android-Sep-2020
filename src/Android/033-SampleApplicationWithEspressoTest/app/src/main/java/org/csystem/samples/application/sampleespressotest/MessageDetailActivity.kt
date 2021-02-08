package org.csystem.samples.application.sampleespressotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MessageDetailActivity : AppCompatActivity() {
    private lateinit var mEditTextMessage: EditText

    private fun initMessageEditText()
    {
        mEditTextMessage = findViewById(R.id.otherActivityEditTextMessage)
        mEditTextMessage.setText(intent.getStringExtra("message"))
    }

    private fun initViews()
    {
        initMessageEditText()
    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
        initialize()
    }
}