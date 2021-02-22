package org.csystem.samples.application.todolist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.todolist.databinding.ActivityMainBinding
import org.csystem.samples.application.todolist.model.Todo
import org.csystem.util.io.FileAccess
import org.csystem.util.io.FileStream
import java.io.EOFException
import java.io.File
import java.io.IOException

const val g_filename = "todo.dat"

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mFileStream: FileStream

    private fun loadNames() //Aslında asenkron yapılması gerekir
    {
        try {
            if (mFileStream.length() == 0L) {
                Toast.makeText(this, "no data yet!...", Toast.LENGTH_LONG).show()
                return
            }
            val adapter = ArrayAdapter<Todo>(this, android.R.layout.simple_list_item_1, ArrayList())

            mFileStream.seekSet()
            try {
                while (true) {
                    val todo = mFileStream.readObject() as Todo
                    adapter.add(todo)
                }
            }
            catch (ignore: EOFException) {

            }

            mBinding.mainActivityListViewTodos.adapter = adapter
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
    }

    private fun onAddButtonClicked(view: View)
    {
        try {
            val title = mBinding.mainActivityEditTextTitle.text.toString()
            val description = mBinding.mainActivityEditTextDescription.text.toString()

            mFileStream.seekEnd()
            mFileStream.writeObject(Todo(title, description))

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

    override fun onResume()
    {
        try {
            mFileStream = FileStream(File(filesDir, g_filename), FileAccess.READWRITE)
            loadNames()
        }
        catch (ex: IOException) {
            Toast.makeText(this, "internal error!...", Toast.LENGTH_LONG).show()
            finish()
        }
        super.onResume()
    }

    override fun onPause()
    {
        try {
            mFileStream.close()
        }
        catch (ex: IOException) {
            Toast.makeText(this, "internal error!...", Toast.LENGTH_LONG).show()
        }
        super.onPause()
    }
}