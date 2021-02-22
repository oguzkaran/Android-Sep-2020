package org.csystem.samples.application.namelist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

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
            Files.newBufferedReader(Paths.get(mNamesFile.absolutePath), StandardCharsets.UTF_8).useLines {
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
            Files.newBufferedWriter(Paths.get(mNamesFile.absolutePath), StandardCharsets.UTF_8, StandardOpenOption.APPEND).use {
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