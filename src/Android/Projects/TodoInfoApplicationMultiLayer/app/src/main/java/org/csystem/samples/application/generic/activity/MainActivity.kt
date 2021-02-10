package org.csystem.samples.application.generic.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.application.todoinfo.data.dto.TodoInfoDTO
import org.csystem.application.todoinfo.data.service.TodoApplicationDataService
import org.csystem.samples.application.generic.R
import org.csystem.samples.application.generic.databinding.ActivityMainBinding
import org.csystem.util.data.service.DataServiceException
import javax.inject.Inject
import org.csystem.util.exception.ExceptionUtil.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mListViewTodo: ListView
    private lateinit var mTodoListAdapter: ArrayAdapter<TodoInfoDTO>
    private lateinit var mBinding: ActivityMainBinding

    @Inject lateinit var todoApplicationDataService : TodoApplicationDataService


    private fun showAboutDialog()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.about_title)
            setMessage(R.string.about_message)
            setPositiveButton(R.string.about_positive_button) { _, _->}
        }.show()
    }

    private fun saveTodo()
    {
        subscribeRunnable({todoApplicationDataService.saveTodo(mBinding.todoInfo!!); loadTodo()},
            {Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()})
    }

    private fun loadTodo()
    {
        subscribeRunnable({mTodoListAdapter.clear();
            todoApplicationDataService.findAllTodos().forEach {mTodoListAdapter.add(it)}},
            {Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()})

    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.todoInfo = TodoInfoDTO()
    }

    private fun initTodoListView()
    {
        mListViewTodo = findViewById(R.id.mainActivityListViewTodos)
        mTodoListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList())
        mListViewTodo.adapter = mTodoListAdapter
    }

    private fun initViews()
    {
        initTodoListView()
    }

    private fun initialize()
    {
        initBinding()
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
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
            R.id.mainActivityMenuItemSaveTodo -> saveTodo()
        }
        return super.onOptionsItemSelected(item)
    }
}