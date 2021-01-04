package org.csystem.samples.application.radiobutton.activity

import android.content.Intent
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
import org.csystem.samples.application.radiobutton.R
import org.csystem.samples.application.radiobutton.activity.model.Info

class MainActivity : AppCompatActivity() {
    companion object {
        const val UPDATE_ACTIVITY_REQUEST_CODE = 1
    }
    private lateinit var mRadioGroupEducation: RadioGroup
    private lateinit var mRadioGroupMaritalStatus: RadioGroup
    private lateinit var mTextViewResult: TextView

    private fun showAboutDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.about_title)
            setMessage(R.string.about_message)
            setPositiveButton(R.string.about_positive_button) { _, _->}
        }.show()
    }

    private fun showNotSelectedDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.notselected_title)
            setMessage(R.string.notselected_message)
            setPositiveButton(R.string.notselected_positive_button) { _, _->}
        }.show()
    }

    private fun onUpdateMenu()
    {
        val maritalStatusIndex = mRadioGroupMaritalStatus.tag as Int

        if (maritalStatusIndex == -1) {
            showNotSelectedDialog()
            return
        }

        val educationIndex = mRadioGroupEducation.tag as Int

        val intent = Intent(this, UpdateActivity::class.java)

        intent.putExtra("info", Info(educationIndex, maritalStatusIndex))
        startActivityForResult(intent, UPDATE_ACTIVITY_REQUEST_CODE)
    }

    private fun initEducationRadioGroup()
    {
        mRadioGroupEducation = findViewById(R.id.mainActivityRadioGroupEducation)
        mRadioGroupEducation.setOnCheckedChangeListener { _, id ->
            val rb = findViewById<RadioButton>(id)
            //...
            mRadioGroupEducation.tag = rb.tag.toString().toInt()

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

            if (rb != null) {
                mRadioGroupMaritalStatus.tag = rb.tag.toString().toInt()
                Toast.makeText(this, rb.text, Toast.LENGTH_LONG).show()
            }
            //TODO: rb'nin null olması durumunda tekrar tag bilgisi güncellenecek ve diğer id'ye göre rütuşlar yapılacaktır
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        when (requestCode) {
            UPDATE_ACTIVITY_REQUEST_CODE -> {
                if (resultCode != RESULT_OK)
                    return

                val info = data?.getSerializableExtra("info") as Info
                (mRadioGroupEducation.getChildAt(info.educationIndex) as RadioButton).isChecked = true
                (mRadioGroupMaritalStatus.getChildAt(info.maritalStatusIndex) as RadioButton).isChecked = true
            }
            //
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId) {
            R.id.mainActivityMenuItemUpdate -> onUpdateMenu()
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

        mRadioGroupMaritalStatus.clearCheck()
    }
}