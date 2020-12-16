package org.csystem.samples.application.eventdriven

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mEditTextName: EditText
    private lateinit var mEditTextSurname: EditText
    private lateinit var mTextViewFullname: TextView

    private data class PersonInfo(val name: String, val surname: String, val isValid: Boolean)

    private fun validate() : PersonInfo
    {
        val name = mEditTextName.text.toString()
        val surname = mEditTextSurname.text.toString()

        if (name.isBlank() || surname.isBlank())
            return PersonInfo(name, surname, false)

        return PersonInfo(name, surname, true)
    }

    private fun okOKButtonsClicked(message: String)
    {
        val (name, surname, isValid) = validate();

        if (!isValid) {
            Toast.makeText(this, "$message:Geçersiz girişler", Toast.LENGTH_LONG).show()
            return
        }
        "$message:$name $surname".also { mTextViewFullname.text = it }
        //...
    }

    private fun initOK2Button()
    {
        val buttonOK2 = findViewById<Button>(R.id.mainActivityButtonOK2)

        buttonOK2.setOnClickListener{okOKButtonsClicked("OK2")}
    }

    private fun initOK3Button()
    {
        val buttonOK3 = findViewById<Button>(R.id.mainActivityButtonOK3)

        buttonOK3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?)
            {
                okOKButtonsClicked("OK3")
            }
        })
    }

    private fun initOK4Button()
    {
        val buttonOK4 = findViewById<Button>(R.id.mainActivityButtonOK4)

        buttonOK4.setOnClickListener(this)
    }

    private fun initButtons()
    {
        initOK2Button()
        initOK3Button()
        initOK4Button()
    }

    private fun initEditTexts()
    {
        mEditTextName = findViewById(R.id.mainActivityEditTextName)
        mEditTextSurname = findViewById(R.id.mainActivityEditTextSurname)
    }

    private fun initTextViews()
    {
        mTextViewFullname = findViewById(R.id.mainActivityTextViewFullname)
    }

    private fun initViews()
    {
        initEditTexts()
        initTextViews()
        initButtons()
    }

    private fun initialize()
    {
        initViews()
        //...
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    override fun onClick(p0: View?)
    {
        okOKButtonsClicked("OK4")
    }

    fun onOKButtonClicked(view: View)
    {
       okOKButtonsClicked("OK")
    }

    fun onExitButtonClicked(view: View)
    {
        finish()
    }

    fun onFullnameTextViewClicked(view: View)
    {
        if (mTextViewFullname.text.isNotBlank())
            Toast.makeText(this, mTextViewFullname.text, Toast.LENGTH_SHORT).show()
    }
}