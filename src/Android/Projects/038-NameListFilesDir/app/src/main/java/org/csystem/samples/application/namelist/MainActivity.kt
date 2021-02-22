package org.csystem.samples.application.namelist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextName: EditText
    private lateinit var mListViewNames: ListView
    private lateinit var mFilesDir: File
    private lateinit var mNamesFile: File

    private fun loadNames() //Aslında asenkron yapılması gerekir
    {
        try {
            if (!mNamesFile.exists()) {
                Toast.makeText(this, "Not data yet!...", Toast.LENGTH_LONG).show()
                return
            }

            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayList())

            BufferedReader(FileReader(mNamesFile)).useLines { //Default charset kullanır
                it.forEach { adapter.add(it) }
            }
            mListViewNames.adapter = adapter
        }
        catch (ex: Throwable) {
            Toast.makeText(this, "Internal error occurs!...", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun initNameEditText()
    {
        mEditTextName = findViewById(R.id.mainActivityEditTextTextName)
    }

    private fun initNamesListView()
    {
        mListViewNames = findViewById(R.id.mainActivityListViewNames)
    }

   // @RequiresApi(Build.VERSION_CODES.O)
    private fun initialize()
    {
        mFilesDir = filesDir // Örnek açısından ayrı yapıldı
        mNamesFile = File(mFilesDir, "names.dat")
        initNameEditText()
        initNamesListView()
        loadNames()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    fun onAddButtonClicked(view: View)
    {
        try {
            BufferedWriter(FileWriter(mNamesFile, true)).also {
                it.write(mEditTextName.text.toString())
                it.newLine()
                it.flush()
            }
            loadNames()
        }
        catch (ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }
}