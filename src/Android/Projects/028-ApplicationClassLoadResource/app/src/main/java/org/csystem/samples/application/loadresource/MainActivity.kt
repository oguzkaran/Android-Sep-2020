package org.csystem.samples.application.loadresource

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.loadresource.application.LoadResourceApplication
import kotlin.random.Random

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{
    private lateinit var mLinearLayoutMain: LinearLayout
    private lateinit var mTextViewColor: TextView
    private lateinit var mSpinnerColors: Spinner

    private fun showAboutDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.about_title)
            setMessage(R.string.about_message)
            setPositiveButton(R.string.about_positive_button) {_, _->}
        }.show()
    }

    private fun initColorSpinner()
    {
        mSpinnerColors = findViewById(R.id.mainActivitySpinnerColors)
        mSpinnerColors.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, LoadResourceApplication.COLORS)
        mSpinnerColors.onItemSelectedListener = this
    }

    private fun initViews()
    {
        mLinearLayoutMain = findViewById(R.id.mainActivityLinearLayoutMain)
        initColorSpinner()
        mTextViewColor = findViewById(R.id.mainActivityTextViewColor)
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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long)
    {
        mLinearLayoutMain.setBackgroundColor(LoadResourceApplication.COLORS[pos].color)
    }

    override fun onNothingSelected(p0: AdapterView<*>?)
    {
        Toast.makeText(this, "Nothing selected", Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId) {
            R.id.mainActivityMenuItemAbout -> showAboutDialog()
            R.id.mainActivityMenuItemExit -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun onOKButtonClicked(view: View)
    {
        val colorInfo = mSpinnerColors.selectedItem as ColorInfo

        mTextViewColor.setBackgroundColor(Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
        "Zemin rengi:${colorInfo.name}".also { mTextViewColor.text = it }
    }
}