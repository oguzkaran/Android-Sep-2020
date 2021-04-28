package org.csystem.samples.application.samplesqlite

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.samplesqlite.data.dal.SampleSqLiteAppDAL
import org.csystem.samples.application.samplesqlite.data.entity.User
import org.csystem.samples.application.samplesqlite.databinding.ActivityAllUsersBinding
import org.csystem.util.data.repository.RepositoryException

class AllUsersActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityAllUsersBinding
    private lateinit var mSQLiteAppDDAL: SampleSqLiteAppDAL

    private fun loadUsers(f: () -> Unit) //TODO: Asenkron yapılacak
    {
        try {
            f()
        }
        catch (ex: RepositoryException) {
            Toast.makeText(this, R.string.error_in_db_text, Toast.LENGTH_LONG).show()
            Log.d("loadUsers.exception", ex.message!!)
            throw ex
        }
    }

    private fun allUsersCallback()
    {
        mBinding.allUsersActivityListViewUsers.also {lvu ->
            mSQLiteAppDDAL.open().use {
                val users = it.findUsersSortByRegisterDate().toList()

                if (users.isNotEmpty())
                    lvu.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
                else {
                    Toast.makeText(this, R.string.no_users_text, Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }

    private fun findUserCallback(username: String)
    {
        mBinding.allUsersActivityListViewUsers.also {lvu ->
            mSQLiteAppDDAL.open().use {
                val user = it.findUserByUsername(username)

                if (user.isPresent)
                    lvu.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOf(user.get()))
                else
                    Toast.makeText(this, R.string.no_users_text, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun findUsersCallback(text: String)
    {
        mBinding.allUsersActivityListViewUsers.also {lvu ->
            mSQLiteAppDDAL.open().use {
                val users = it.findUsersByUsernameContains(text).toList()

                if (users.isNotEmpty())
                    lvu.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
                else
                    Toast.makeText(this, R.string.no_users_text, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onPositiveButtonClicked()
    {
        mSQLiteAppDDAL.open().use {
            val count = it.deleteAllUsers()
            mBinding.allUsersActivityListViewUsers.adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<User>())

            Toast.makeText(this, "$count users deleted", Toast.LENGTH_LONG).show()
        }
        finish()
    }

    private fun onDeleteAllUsersButtonClicked()
    {
        try {
            AlertDialog.Builder(this).apply {
                setTitle("Alert")
                setMessage("Are you sure to delete all users?")
                    .setPositiveButton("Evet") {x, y -> onPositiveButtonClicked()}
                    .setNegativeButton("İptal") {x, y ->}
                    .create()
            }.show()
        }
        catch (ex: RepositoryException) {
            Toast.makeText(this, R.string.error_in_db_text, Toast.LENGTH_LONG).show()
            Log.d("onDelete.exception", ex.message!!)
        }
    }

    private fun onFindButtonClicked()
    {
        try {
            var id = mBinding.allUsersActivityRadioGroupSelection.checkedRadioButtonId

            mSQLiteAppDDAL.open().use {
                val text = mBinding.allUsersActivityEditTextUsername.text.toString()
                var block = this::findUsersCallback

                if (id == R.id.allUsersActivityRadioButtonFindUser)
                    block = this::findUserCallback

                loadUsers { block(text) }
            }
        }
        catch (ex: RepositoryException) {
            Toast.makeText(this, R.string.error_in_db_text, Toast.LENGTH_LONG).show()
            Log.d("onFind.exception", ex.message!!)
        }
    }

    private fun onAllUsersButtonClicked() = loadUsers{allUsersCallback()}

    private fun initButtons()
    {
        mBinding.allUsersActivityButtonDeleteAllUsers.setOnClickListener { onDeleteAllUsersButtonClicked() }
        mBinding.allUsersActivityButtonFind.setOnClickListener { onFindButtonClicked() }
        mBinding.allUsersActivityButtonAllUsers.setOnClickListener { onAllUsersButtonClicked() }
    }

    private fun initUsersList()
    {
        loadUsers{allUsersCallback()}
    }

    private fun initViews()
    {
        initButtons()
        initUsersList()
    }

    private fun initBindings()
    {
        mBinding = ActivityAllUsersBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBindings()
        mSQLiteAppDDAL = SampleSqLiteAppDAL(this)
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}