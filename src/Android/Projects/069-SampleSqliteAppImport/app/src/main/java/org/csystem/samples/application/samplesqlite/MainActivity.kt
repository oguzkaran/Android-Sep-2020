package org.csystem.samples.application.samplesqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.samplesqlite.data.dal.SampleSqLiteAppDAL
import org.csystem.samples.application.samplesqlite.databinding.ActivityMainBinding
import org.csystem.util.data.repository.RepositoryException

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mSQLiteAppDDAL: SampleSqLiteAppDAL //TODO: Dependency injection yapılacak

    private fun initButtons()
    {
        mBinding.mainActivityButtonRegister.setOnClickListener { startActivity(Intent(this, RegisterActivity::class.java)) }
        mBinding.mainActivityButtonAllUsers.setOnClickListener { startActivity(Intent(this, AllUsersActivity::class.java)) }
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
}