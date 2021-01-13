package org.csystem.samples.application.sampleaar

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.util.application.App
import org.csystem.samples.application.sampleaar.application.MyApplication
import org.csystem.samples.application.sampleaar.databinding.ActivityMainBinding
import org.csystem.samples.application.sampleaar.viewmodel.Data
import org.csystem.samples.library.sample.AboutActivity
import org.csystem.util.exception.ExceptionUtil

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun showAboutDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.about_title)
            setMessage(R.string.about_message)
            setPositiveButton(R.string.about_positive_button) {_, _->}
        }.show()
    }

    private fun initBinding()
    {
        ExceptionUtil.subscribeRunnable({mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main); mBinding.data = Data()},
            {Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()})
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
            R.id.mainActivityMenuItemDisplay -> Toast.makeText(App.getInstance(), mBinding.data.toString(), Toast.LENGTH_LONG).show()
            R.id.mainActivityMenuItemOpenAboutActivity -> startActivity(Intent(this, AboutActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}