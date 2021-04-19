package org.csystem.samples.application.samplesqlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.samplesqlite.data.dal.SampleSqLiteAppDAL
import org.csystem.samples.application.samplesqlite.data.entity.User
import org.csystem.samples.application.samplesqlite.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mSQLiteAppDDAL: SampleSqLiteAppDAL

    private fun onRegisterButtonClicked()
    {
        val name = mBinding.registerActivityEditTextName.text.toString()
        val username = mBinding.registerActivityEditTextUsername.text.toString()
        val password = mBinding.registerActivityEditTextPassword.text.toString()

        val user = User(name = name, username = username, password = password)

        mSQLiteAppDDAL.open().use { it.saveUser(user) }

        Toast.makeText(this, user.userId.toString(), Toast.LENGTH_LONG).show()
    }

    private fun initButtons()
    {
        mBinding.registerActivityButtonSave.setOnClickListener { onRegisterButtonClicked() }
    }

    private fun initViews()
    {
        initButtons()
    }

    private fun initBinding()
    {
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
        initViews()
        mSQLiteAppDDAL = SampleSqLiteAppDAL(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }
}