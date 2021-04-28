package org.csystem.samples.application.sampleobjectbox.application

import android.app.Application
import org.csystem.samples.application.sampleobjectbox.data.objectbox.ObjectBox

class SampleObjectBoxApplication : Application() {
    override fun onCreate() {
        ObjectBox.initialize(this)
        super.onCreate()
    }
}