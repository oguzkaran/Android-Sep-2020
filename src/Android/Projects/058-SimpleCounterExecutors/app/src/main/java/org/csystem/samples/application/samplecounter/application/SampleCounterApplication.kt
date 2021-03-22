package org.csystem.samples.application.samplecounter.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@HiltAndroidApp
class SampleCounterApplication : Application() {
    companion object {
        private lateinit var mThreadPool: ExecutorService
        val threadPool get() = mThreadPool
    }

    override fun onCreate()
    {
        mThreadPool = Executors.newCachedThreadPool()
        super.onCreate()
    }
}