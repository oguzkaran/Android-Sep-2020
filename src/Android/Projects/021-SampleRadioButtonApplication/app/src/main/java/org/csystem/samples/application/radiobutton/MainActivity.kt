package org.csystem.samples.application.radiobutton

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mRadioGroupEducation: RadioGroup
    private lateinit var mRadioGroupMaritalStatus: RadioGroup
    private lateinit var mTextViewResult: TextView

    private fun showAboutDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.about_title)
            setMessage(R.string.about_message)
            setPositiveButton(R.string.about_positive_button) {_, _->}
        }.show()
    }

    private fun showNotSelectedDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.notselected_title)
            setMessage(R.string.notselected_message)
            setPositiveButton(R.string.notselected_positive_button) {_, _->}
        }.show()
    }

    private fun initEducationRadioGroup()
    {
        mRadioGroupEducation = findViewById(R.id.mainActivityRadioGroupEducation)
        mRadioGroupEducation.setOnCheckedChangeListener { _, id ->
            val rb = findViewById<RadioButton>(id)
            //...

            if (rb != null)
                Toast.makeText(this, rb.text, Toast.LENGTH_LONG).show()
        }
    }

    private fun initMaritalStatusRadioGroup()
    {
        mRadioGroupMaritalStatus = findViewById(R.id.mainActivityRadioGroupMaritalStatus)
        mRadioGroupMaritalStatus.setOnCheckedChangeListener { _, id ->
            val rb = findViewById<RadioButton>(id)

            //...

            Toast.makeText(this, rb.text, Toast.LENGTH_LONG).show()
        }
    }

    private fun initViews()
    {
        initEducationRadioGroup()
        initMaritalStatusRadioGroup()
        mTextViewResult = findViewById(R.id.mainActivityTextViewResult)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId) {
            R.id.mainActivityMenuItemAbout -> showAboutDialog()
            R.id.mainActivityMenuItemExit -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun onSaveButtonClicked(view: View)
    {
        val maritalStatusSelectedId = mRadioGroupMaritalStatus.checkedRadioButtonId

        if (maritalStatusSelectedId == -1) {
            showNotSelectedDialog()
            return
        }

        val selectedRadioButtonEducation = findViewById<RadioButton>(mRadioGroupEducation.checkedRadioButtonId)
        val selectedRadioButtonMaritalStatus = findViewById<RadioButton>(maritalStatusSelectedId)

        "${selectedRadioButtonEducation.text}, ${selectedRadioButtonMaritalStatus.text}"
            .also { mTextViewResult.text = it }

        //mRadioGroupEducation.clearCheck()
    }
}