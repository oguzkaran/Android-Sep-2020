package org.csystem.samples.application.namelist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.namelist.databinding.ActivityMainBinding
import java.io.*
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mFileNames: File

    private fun loadNames() //Aslında asenkron yapılması gerekir
    {
        try {
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayList())

            FileInputStream(mFileNames).use {
                BufferedReader(InputStreamReader(it, StandardCharsets.UTF_8))
                    .useLines { it.forEach { adapter.add(it) } }
            }

            mBinding.mainActivityListViewNames.adapter = adapter
        }
        catch (ignore: FileNotFoundException) {
            Toast.makeText(this, "No data yet!...", Toast.LENGTH_LONG).show()
        }
        catch (ex: Throwable) {
            Toast.makeText(this, "Internal error occurs!...", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun initViews()
    {
        mBinding.mainActivityButtonAdd.setOnClickListener { onAddButtonClicked(it) }
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        mFileNames = File(cacheDir, "names.txt")
        initBinding()
        initViews()
        loadNames()
    }

    private fun onAddButtonClicked(view: View)
    {
        try {
            FileOutputStream(mFileNames, true).use {
                BufferedWriter(OutputStreamWriter(it, StandardCharsets.UTF_8)).apply {
                    this.write(mBinding.mainActivityEditTextTextName.text.toString())
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

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }


}