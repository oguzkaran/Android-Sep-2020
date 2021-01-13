package org.csystem.samples.application.loadresource.application

import android.app.Application
import org.csystem.samples.application.loadresource.ColorInfo
import org.csystem.samples.application.loadresource.R

class LoadResourceApplication : Application() {
    companion object {
        private lateinit var mColors : Array<ColorInfo>
        val COLORS: Array<ColorInfo>
            get() = mColors
    }

    private fun initColors()
    {
        mColors = arrayOf(
            ColorInfo(getColor(R.color.white), "Beyaz"),
            ColorInfo(getColor(R.color.red), "Kırmızı"),
            ColorInfo(getColor(R.color.green), "Yeşil"),
            ColorInfo(getColor(R.color.blue), "Mavi"),
        )
    }
    override fun onCreate()
    {
        initColors()
        super.onCreate()
    }
}