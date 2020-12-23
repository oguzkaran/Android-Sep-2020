package org.csystem.samples.application.birthdateapplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.lang.StringBuilder
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextName : EditText
    private lateinit var mEditTextBirthDate : EditText
    private lateinit var mTextViewPerson: TextView
    private val mFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private var mIsUpdated: Boolean = false
    private val mOnTextChangCallback: (CharSequence?, Int, Int, Int) -> Unit = {_, _, _, _ -> mIsUpdated = true}

    private fun getBirthDateMessage(birthDay: LocalDate, now: LocalDate) = when {
        now.isAfter(birthDay) -> "Geçmiş doğum gününüz kutlu olsun"
        now.isBefore(birthDay) -> "Doğum gününüzü şimdiden kutlu olsun"
        else -> "Doğum gününüz kutlu olsun"
    }

    private fun clearEditTexts()
    {
        mEditTextName.setText("")
        mEditTextBirthDate.setText("")
    }

    private fun showUpdatedAlert()
    {
        val dlg = android.app.AlertDialog.Builder(this)
                .setTitle("Uyarı")
                .setMessage("Hesaplanmamış giriş var. Çıkmak istediğinize emin misiniz?")
                .setPositiveButton("Evet") { _, _  -> finish()}
                .setNeutralButton("İptal") {_, _ -> }
                .create()

        dlg.show()
    }

    private fun showInvalidFormatAlert()
    {
        val dlg = android.app.AlertDialog.Builder(this)
                .setTitle("Dikkat")
                .setMessage("Geçersiz tarih formatı")
                .setPositiveButton("Tamam") { _, _  -> }
                .create()

        dlg.show()
    }

    private fun initNameEditText()
    {
        mEditTextName = findViewById(R.id.mainActivityEditTextName)
        mEditTextName.addTextChangedListener(onTextChanged = mOnTextChangCallback)
    }

    private fun  initBirthDateEditText()
    {
        mEditTextBirthDate = findViewById(R.id.mainActivityEditTextBirthDate)
        mEditTextBirthDate.addTextChangedListener(onTextChanged = mOnTextChangCallback)
    }

    private fun initViews()
    {
        initNameEditText()
        initBirthDateEditText()
        mTextViewPerson = findViewById(R.id.mainActivityTextViewPerson)
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

    fun onPersonTextViewClicked(view: View)
    {
        val person = mTextViewPerson.tag as Person
        val now = LocalDate.now()
        val birthDayMessage = getBirthDateMessage(person.birthDate.withYear(now.year), now)

        "[${person.name}] ${person.birthDate}-${person.age}\n${birthDayMessage}"
                .also { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
    }

    fun onOKButtonClicked(view: View)
    {
        try {
            mIsUpdated = false;
            val name = mEditTextName.text.toString()
            val birthDate = LocalDate.parse(mEditTextBirthDate.text.toString(), mFormatter)
            clearEditTexts()
            mTextViewPerson.tag = Person(name, birthDate)
            mTextViewPerson.text = name
        }
        catch (ex:DateTimeParseException) {
            showInvalidFormatAlert()
        }
    }

    fun onExitButtonClicked(view: View)
    {
        if (mIsUpdated)
            showUpdatedAlert()
        else
            finish()
    }
}