package org.csystem.samples.application.preferences

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.csystem.samples.application.preferences.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private var mBackgroundColor: Int = Color.WHITE
    private lateinit var mSharedPrefs: SharedPreferences

    private fun onOpenSecondActivityButtonClicked()
    {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    private fun onChangeColorButtonClicked()
    {
        mBackgroundColor = Color.rgb(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))

        mBinding.mainActivityLinearLayoutMain.setBackgroundColor(mBackgroundColor)
    }

    private fun onChangeTitleButtonClicked()
    {
        title = mBinding.mainActivityEditTextTitle.text.toString()
    }

    private fun initSettings()
    {
        mSharedPrefs = getPreferences(MODE_PRIVATE)
        mBackgroundColor = mSharedPrefs.getInt("bgcolor", Color.WHITE)
        title = mSharedPrefs.getString("title", resources.getString(R.string.app_name))
        mBinding.mainActivityLinearLayoutMain.setBackgroundColor(mBackgroundColor)
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
        initSettings()
        mBinding.mainActivityButtonChangeColor.setOnClickListener { onChangeColorButtonClicked() }
        mBinding.mainActivityButtonChangeTitle.setOnClickListener { onChangeTitleButtonClicked() }
        mBinding.mainActivityButtonOpenSecondActivity.setOnClickListener { onOpenSecondActivityButtonClicked() }
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onDestroy()
    {
        val edit = mSharedPrefs.edit()

        edit.putString("title", title.toString())
        edit.putInt("bgcolor", mBackgroundColor)
        edit.apply()
        super.onDestroy()
    }
}