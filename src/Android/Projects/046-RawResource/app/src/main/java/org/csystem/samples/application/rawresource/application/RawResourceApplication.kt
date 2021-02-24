package org.csystem.samples.application.rawresource.application

import android.app.Application
import org.csystem.android.util.resource.ResourcesUtil
import org.csystem.samples.application.rawresource.R

class RawResourceApplication : Application() {
    private fun loadRawResources()
    {
        ResourcesUtil.copyRawResourceToFilesDir(this, R.raw.cities, "cities.txt")
    }

    override fun onCreate()
    {
        loadRawResources()
        super.onCreate()
    }
}