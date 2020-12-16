package org.csystem.samples.application.simplepersoninfo

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.simplepersoninfo.model.Person
import org.csystem.samples.application.simplepersoninfo.model.global.PersonValidityUtil

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextName: EditText
    private lateinit var mEditTextCitizenId: EditText
    private lateinit var mEditTextPhone: EditText
    private lateinit var mTextViewPersonInfo: TextView

    private fun getPerson() : Person?
    {
        val name = mEditTextName.text.toString()
        val citizenId = mEditTextCitizenId.text.toString()
        val phone = mEditTextPhone.text.toString()

        if (!PersonValidityUtil.validate(name, citizenId, phone))
            return null

        return Person(name, citizenId, phone)
    }

    private fun clearEditTexts()
    {
        mEditTextName.setText("")
        mEditTextCitizenId.setText("")
        mEditTextPhone.setText("")
    }

    private fun clearAll()
    {
        clearEditTexts()
        mTextViewPersonInfo.text = ""
    }

    private fun initEditTexts()
    {
        mEditTextName = findViewById(R.id.mainActivityEditTextName)
        mEditTextCitizenId = findViewById(R.id.mainActivityEditTextCitizenId)
        mEditTextPhone = findViewById(R.id.mainActivityEditTextPhone)
    }

    private fun initTextViews()
    {
        mTextViewPersonInfo = findViewById(R.id.mainActivityTextViewPersonInfo)
    }

    private fun initViews()
    {
        initEditTexts()
        initTextViews()
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

    fun onSaveButtonClicked(view: View)
    {
        try {
            val p = getPerson()

            if (p == null) {
                Toast.makeText(this, "Geçersiz girişler", Toast.LENGTH_LONG).show()
                return
            }

            clearEditTexts()

            mTextViewPersonInfo.tag = p
            mTextViewPersonInfo.text = p.name
        }
        catch (ex: Throwable) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    fun onClearButtonClicked(view: View) = clearAll()

    fun onExitButtonClicked(view: View) = finish()
    fun onPersonInfoTextViewClicked(view: View)
    {
        val person = mTextViewPersonInfo.tag as Person

        Toast.makeText(this, person.toString(), Toast.LENGTH_LONG).show()
    }

}