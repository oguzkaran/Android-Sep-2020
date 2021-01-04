package org.csystem.samples.application.radiobutton.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.radiobutton.R
import org.csystem.samples.application.radiobutton.activity.model.Info

class UpdateActivity : AppCompatActivity() {
    private lateinit var mRadioGroupEducation: RadioGroup
    private lateinit var mRadioGroupMaritalStatus: RadioGroup
    private lateinit var mInfo: Info

    private fun initEducationRadioGroup()
    {
        mRadioGroupEducation = findViewById(R.id.updateActivityRadioGroupEducation)
        mRadioGroupEducation.setOnCheckedChangeListener { _, id ->
            val rb = findViewById<RadioButton>(id)
            mRadioGroupEducation.tag = rb.tag.toString().toInt()
        }
    }

    private fun initMaritalStatusRadioGroup()
    {
        mRadioGroupMaritalStatus = findViewById(R.id.updateActivityRadioGroupMaritalStatus)
        mRadioGroupMaritalStatus.setOnCheckedChangeListener { _, id ->
            val rb = findViewById<RadioButton>(id)
            mRadioGroupMaritalStatus.tag = rb.tag.toString().toInt()
        }
    }

    private fun initViews()
    {
        initEducationRadioGroup()
        initMaritalStatusRadioGroup()
        mInfo = intent.getSerializableExtra("info") as Info
        (mRadioGroupEducation.getChildAt(mInfo.educationIndex) as RadioButton).isChecked = true
        (mRadioGroupMaritalStatus.getChildAt(mInfo.maritalStatusIndex) as RadioButton).isChecked = true
    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        initialize()
    }

    fun onUpdateButtonClicked(view: View)
    {
        mInfo.educationIndex = mRadioGroupEducation.tag as Int
        mInfo.maritalStatusIndex = mRadioGroupMaritalStatus.tag as Int
        setResult(RESULT_OK, Intent().apply { putExtra("info", mInfo) })
        finish()
    }

    fun onCancelButtonClicked(view: View)
    {
        finish()
    }
}