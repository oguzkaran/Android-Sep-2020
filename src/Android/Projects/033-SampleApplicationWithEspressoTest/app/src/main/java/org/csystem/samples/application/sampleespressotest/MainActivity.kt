package org.csystem.samples.application.sampleespressotest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextMessage: EditText

    private fun initViews()
    {
        mEditTextMessage = findViewById(R.id.mainActivityEditTextMessage)
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

    fun onReverseButtonClicked(view: View)
    {
        mEditTextMessage.setText(mEditTextMessage.text.toString().reversed())
    }

    fun onOpenMessageDetailActivityButtonClicked(view: View)
    {
        val message = mEditTextMessage.text.toString()
        startActivity(Intent(this, MessageDetailActivity::class.java).putExtra("message", message))
    }

    //...
}