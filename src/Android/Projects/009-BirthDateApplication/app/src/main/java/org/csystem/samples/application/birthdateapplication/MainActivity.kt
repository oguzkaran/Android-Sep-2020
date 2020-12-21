package org.csystem.samples.application.birthdateapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextBirthDate : EditText
    private lateinit var mTextViewAge: TextView
    private val mFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    private fun initViews()
    {
        mEditTextBirthDate = findViewById(R.id.mainActivityEditTextBirthDate)
        mTextViewAge = findViewById(R.id.mainActivityTextViewAge)
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

    fun onOKButtonClicked(view: View)
    {
        try {
            val birthDate = LocalDate.parse(mEditTextBirthDate.text.toString(), mFormatter)
            val now = LocalDate.now()
            val birthDay = birthDate.withYear(now.year)
            val age = ChronoUnit.DAYS.between(birthDate, now) / 365.0

            if (age < 0)
                throw DateTimeParseException("", "", 0);

            val message = when {
                now.isAfter(birthDay) -> "Geçmiş doğum gününüz kutlu olsun"
                now.isBefore(birthDay) -> "Doğum gününüzü şimdiden kutlu olsun"
                else -> "Doğum gününüz kutlu olsun"
            }

            mTextViewAge.setText("Yeni yaşınız:%s".format(age))
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
        catch (ex:DateTimeParseException) {
            Toast.makeText(this, "Geçersiz tarih formatı", Toast.LENGTH_LONG).show()
        }
    }

    fun onExitButtonClicked(view: View)
    {
        finish()
    }
}