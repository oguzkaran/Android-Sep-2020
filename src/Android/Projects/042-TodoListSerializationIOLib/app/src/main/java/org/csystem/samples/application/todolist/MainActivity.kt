package org.csystem.samples.application.todolist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.todolist.databinding.ActivityMainBinding
import org.csystem.samples.application.todolist.model.Todo
import org.csystem.util.io.IOUtil
import java.io.*

const val g_filename = "todo.dat"

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding

    private fun loadNames() //Aslında asenkron yapılması gerekir
    {
        try {
            val adapter = ArrayAdapter<Todo>(this, android.R.layout.simple_list_item_1, ArrayList())

            openFileInput(g_filename).use {
                try {
                    while (true)
                        adapter.add(IOUtil.deserialize<Todo>(it))
                }
                catch (ignore: EOFException) {

                }
            }

            mBinding.mainActivityListViewTodos.adapter = adapter
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
        initBinding()
        initViews()
        loadNames()
    }

    private fun onAddButtonClicked(view: View)
    {
        try {
            val title = mBinding.mainActivityEditTextTitle.text.toString()
            val description = mBinding.mainActivityEditTextDescription.text.toString()

            openFileOutput(g_filename, MODE_APPEND).use {
                IOUtil.serialize(it, Todo(title, description))
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