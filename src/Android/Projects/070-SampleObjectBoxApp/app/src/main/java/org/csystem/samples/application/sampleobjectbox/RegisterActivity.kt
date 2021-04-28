package org.csystem.samples.application.sampleobjectbox

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.sampleobjectbox.data.dal.SampleObjectBoxAppDAL
import org.csystem.samples.application.sampleobjectbox.data.entity.User
import org.csystem.samples.application.sampleobjectbox.databinding.ActivityRegisterBinding
import org.csystem.util.data.repository.RepositoryException

class RegisterActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mObjectBoxAppDDAL: SampleObjectBoxAppDAL

    private fun onRegisterButtonClicked()
    {
        try {
            mObjectBoxAppDDAL.open().use {
                val username = mBinding.registerActivityEditTextUsername.text.toString()
                if (!it.existsByUsername(username)) {
                    val name = mBinding.registerActivityEditTextName.text.toString()
                    val password = mBinding.registerActivityEditTextPassword.text.toString()
                    val user = User(name = name, username = username, password = password)
                    it.saveUser(user)
                    Toast.makeText(this, user.id.toString(), Toast.LENGTH_LONG).show()
                    finish()
                }
                else
                    Toast.makeText(this, R.string.error_existinguser_text, Toast.LENGTH_LONG).show()
            }
        }
        catch (ex: RepositoryException) {
            Toast.makeText(this, R.string.error_in_db_text, Toast.LENGTH_LONG).show()
        }
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
        mObjectBoxAppDDAL = SampleObjectBoxAppDAL(this)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}