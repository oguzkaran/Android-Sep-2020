package org.csystem.samples.application.samplelistview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextCityName: EditText
    private lateinit var mEditTextCityPlate: EditText
    private lateinit var mEditTextCityPhoneCode: EditText
    private lateinit var mListViewCities: ListView
    private lateinit var mCitiesAdapter: ArrayAdapter<CityInfo>
    private lateinit var mButtonClear: Button

    private fun showAboutDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.about_title)
            setMessage(R.string.about_message)
            setPositiveButton(R.string.about_positive_button) {_, _->}
        }.show()
    }

    private fun showInvalidDataDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.invalid_data_title)
            setMessage(R.string.invalid_data_message)
            setPositiveButton(R.string.invalid_data_positive_button) {_, _->}
        }.show()
    }

    private fun initNamesListView()
    {
        mListViewCities = findViewById(R.id.mainActivityListViewNames)
        mCitiesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<CityInfo>())
        mListViewCities.adapter = mCitiesAdapter
        mListViewCities.setOnItemClickListener { _, _, pos, _ ->
            val (name, plate, phoneInfo) = mCitiesAdapter.getItem(pos)!!

            setTitle("$name, $plate, $phoneInfo")
        }
    }

    private fun initEditTexts()
    {
        mEditTextCityName = findViewById(R.id.mainActivityEditTextCityName)
        mEditTextCityPlate = findViewById(R.id.mainActivityEditTextCityPlate)
        mEditTextCityPhoneCode = findViewById(R.id.mainActivityEditTextCityPhoneCode)
    }

    private fun initViews()
    {
        initNamesListView()
        initEditTexts()
        mButtonClear = findViewById(R.id.mainActivityButtonClear)
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

    fun onAddWithAdapterButtonClicked(view: View)
    {
        try {
            val name = mEditTextCityName.text.toString()
            val plate = mEditTextCityPlate.text.toString().toInt()
            val phoneCode = mEditTextCityPhoneCode.text.toString().toInt()

            mCitiesAdapter.add(CityInfo(name, plate, phoneCode))
            mButtonClear.isEnabled = true
        }
        catch (ex: NumberFormatException) {
            showInvalidDataDialog()
        }
    }

    fun onClearCitiesButtonClicked(view: View)
    {
        mButtonClear.isEnabled = false
        mCitiesAdapter.clear()
    }
}