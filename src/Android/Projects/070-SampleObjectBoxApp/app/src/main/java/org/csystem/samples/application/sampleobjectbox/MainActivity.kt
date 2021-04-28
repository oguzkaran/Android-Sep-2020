package org.csystem.samples.application.sampleobjectbox

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.sampleobjectbox.data.dal.SampleObjectBoxAppDAL
import org.csystem.samples.application.sampleobjectbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mObjectBoxAppDDAL: SampleObjectBoxAppDAL //TODO: Dependency injection yapılacak

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
        mObjectBoxAppDDAL = SampleObjectBoxAppDAL(this)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}