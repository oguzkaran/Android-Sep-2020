package org.csystem.application.android.raspberry.panicclient

import android.app.Application
import org.csystem.application.android.raspberry.panicclient.global.startOKService

class AndroidPanicClientApplication : Application() {
    override fun onCreate()
    {
        startOKService(this)
        super.onCreate()
    }
}