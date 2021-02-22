package org.csystem.samples.application.namelist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextName: EditText
    private lateinit var mListViewNames: ListView

    private fun loadNames() //Aslında asenkron yapılması gerekir
    {
        try {
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayList())

            openFileInput("names.dat").use {
                BufferedReader(InputStreamReader(it, StandardCharsets.UTF_8))
                        .useLines { it.forEach { adapter.add(it) } }
            }

            mListViewNames.adapter = adapter
        }
        catch (ignore: FileNotFoundException) {
            Toast.makeText(this, "No data yet!...", Toast.LENGTH_LONG).show()
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

    private fun initialize()
    {
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
            openFileOutput("names.dat", MODE_APPEND).use {
                BufferedWriter(OutputStreamWriter(it, StandardCharsets.UTF_8)).apply {
                    this.write(mEditTextName.text.toString())
                    this.newLine()
                    this.flush()
                }
            }
            loadNames()
        }
        catch (ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }
}