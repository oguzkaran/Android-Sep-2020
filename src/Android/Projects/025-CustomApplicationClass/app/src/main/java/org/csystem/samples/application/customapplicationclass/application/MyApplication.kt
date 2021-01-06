package org.csystem.samples.application.customapplicationclass.application

import android.app.Application
import android.widget.Toast

class MyApplication : Application() {
    companion object {
        private lateinit var mcInstance: MyApplication
        val INSTANCE : MyApplication
            get() = mcInstance
    }
    val data = "MyApplication" // MyApplication nesnesi içerisinde olabilecek bir veriyi temsil ediyor

    override fun onCreate()
    {
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
        mcInstance = this
        super.onCreate()
    }
}