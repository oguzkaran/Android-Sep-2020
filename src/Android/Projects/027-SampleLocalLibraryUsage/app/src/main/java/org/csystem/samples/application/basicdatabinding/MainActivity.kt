package org.csystem.samples.application.basicdatabinding

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.samples.application.basicdatabinding.databinding.ActivityMainBinding
import org.csystem.samples.application.basicdatabinding.viewmodel.Device
import org.csystem.util.exception.ExceptionUtil
import java.lang.IllegalArgumentException
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun showDialogProc()
    {
        if (Random.nextBoolean())
            throw IllegalArgumentException("Merhaba ben exception")

        AlertDialog.Builder(this).apply {
            setTitle(R.string.about_title)
            setMessage(R.string.about_message)
            setPositiveButton(R.string.about_positive_button) { _, _ -> }
        }.show()
    }

    private fun showAboutDialog()
    {
        ExceptionUtil.subscribeRunnable({showDialogProc()}, {Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()})
    }

    private fun initSpinner()
    {
        findViewById<Spinner>(R.id.mainActivitySpinnerStatus).also {
            it.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, DeviceStatus.values())
        }
    }

    private fun initViews()
    {
        initSpinner()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.device = Device(port = 1024)
    }

    private fun initialize()
    {
        initBinding()
        initViews()
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
            R.id.mainActivityMenuItemDisplay -> Toast.makeText(this, mBinding.device.toString(), Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}