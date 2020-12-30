package org.csystem.samples.application.sampleoptionsmenuapplication.activity

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.sampleoptionsmenuapplication.R
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mLinearLayoutMain: LinearLayout

    private fun showAboutDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.about_title)
            setMessage(R.string.about_message)
            setPositiveButton(R.string.about_positive_button) {_, _->}
        }.show()
    }

    private fun onBackgroundColorMenuItems(item: MenuItem, color: Int = -1)
    {
        if (item.isChecked)
            return
        item.isChecked = !item.isChecked

        mLinearLayoutMain.setBackgroundColor(color)
    }

    private fun initViews()
    {
        mLinearLayoutMain = findViewById(R.id.mainActivityLinearLayoutMain)
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
        menuInflater.inflate(R.menu.mainavtivity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId) {
            R.id.mainActivityMenuItemRed -> onBackgroundColorMenuItems(item, Color.RED)
            R.id.mainActivityMenuItemGreen -> onBackgroundColorMenuItems(item, Color.GREEN)
            R.id.mainActivityMenuItemBlue -> onBackgroundColorMenuItems(item, Color.BLUE)
            R.id.mainActivityMenuItemBlack ->  {
                val color = if (Build.VERSION.SDK_INT >= 23) //Detaylar ileride ele alınacaktır
                    this.getColor(R.color.black)
                else
                    resources.getColor(R.color.black)

                onBackgroundColorMenuItems(item, color)
            }
            R.id.mainActivityMenuItemRandom ->  {
                val color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

                onBackgroundColorMenuItems(item, color)
            }
            R.id.mainActivityMenuItemAbout -> showAboutDialog()
            R.id.mainActivityMenuItemExit -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}