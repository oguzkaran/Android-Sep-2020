package org.csystem.samples.application.generic.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleHiltApplication : Application() {
    val tag = "SampleHiltApplication"
}