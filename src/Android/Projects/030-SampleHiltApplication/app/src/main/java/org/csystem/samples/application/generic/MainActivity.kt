package org.csystem.samples.application.generic

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.samples.application.generic.databinding.ActivityMainBinding
import org.csystem.samples.application.generic.helper.RandomGeneratorHelper
import org.csystem.samples.application.generic.viewmodel.RandomInfo
import org.csystem.samples.application.generic.viewmodel.ResultInfo
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    @Inject lateinit var randomGeneratorHelper: RandomGeneratorHelper

    private fun showAboutDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.about_title)
            setMessage(R.string.about_message)
            setPositiveButton(R.string.about_positive_button) {_, _->}
        }.show()
    }
    private fun onDisplayNumberMenuSelected()
    {
        val randomInfo = mBinding.randomInfo
        val randomResultInfo = mBinding.randomResultInfo!!
        val threadLocalRandomResultInfo = mBinding.threadLocalRandomResultInfo!!

        randomResultInfo.result = randomGeneratorHelper.getRandomNumber(randomInfo!!.min, randomInfo.max)
        threadLocalRandomResultInfo.result = randomGeneratorHelper.getThreadLocalRandomNumber(randomInfo.min, randomInfo.max)
        mBinding.invalidateAll() //Değişikliğin görsele yansıtılması için
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.randomInfo = RandomInfo()
        mBinding.randomResultInfo = ResultInfo()
        mBinding.threadLocalRandomResultInfo = ResultInfo()
    }

    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
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
            R.id.mainActivityMenuItemDisplay -> onDisplayNumberMenuSelected()
        }
        return super.onOptionsItemSelected(item)
    }
}