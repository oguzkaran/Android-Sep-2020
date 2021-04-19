package org.csystem.samples.application.samplesqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.samplesqlite.data.dal.SampleSqLiteAppDAL
import org.csystem.samples.application.samplesqlite.databinding.ActivityMainBinding
import org.csystem.util.data.repository.RepositoryException

private const val REQUEST_REGISTER = 1

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mSQLiteAppDDAL: SampleSqLiteAppDAL
    private fun loadUsers() //asenkron olacak
    {
        try {

        }
        catch (ex: RepositoryException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun initButtons()
    {
        mBinding.mainActivityButtonRegister.setOnClickListener { startActivityForResult(Intent(this, RegisterActivity::class.java), REQUEST_REGISTER) }
        mBinding.mainActivityButtonExit.setOnClickListener { finish() }
    }

    private fun initViews()
    {
        initButtons()
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
        mSQLiteAppDDAL = SampleSqLiteAppDAL(this)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK)
            loadUsers()

        super.onActivityResult(requestCode, resultCode, data)
    }
}