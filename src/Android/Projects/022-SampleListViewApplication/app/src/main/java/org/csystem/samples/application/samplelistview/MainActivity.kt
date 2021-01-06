package org.csystem.samples.application.samplelistview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextName: EditText
    private lateinit var mListViewNames: ListView
    private lateinit var mNamesAdapter: ArrayAdapter<String>
    private val mNameList: ArrayList<String> = ArrayList()

    private fun showAboutDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.about_title)
            setMessage(R.string.about_message)
            setPositiveButton(R.string.about_positive_button) {_, _->}
        }.show()
    }

    private fun initNamesListView()
    {
        mListViewNames = findViewById(R.id.mainActivityListViewNames)
        mNamesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mNameList)
        mListViewNames.adapter = mNamesAdapter
        mListViewNames.setOnItemClickListener { _, _, pos, _ ->
            //val name = mListViewNames.getItemAtPosition(pos) as String
            val name = mNamesAdapter.getItem(pos)
            setTitle(name)
        }
    }

    private fun initViews()
    {
        initNamesListView()
        mEditTextName = findViewById(R.id.mainActivityEditTextName)
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
        mNamesAdapter.add(mEditTextName.text.toString())
        //mNamesAdapter.notifyDataSetChanged() //otomatik çağrılacağı garanti altındadır
    }

    fun onAddWithListButtonClicked(view: View)
    {
        mNameList.add(mEditTextName.text.toString())
        mNamesAdapter.notifyDataSetChanged()
    }
}