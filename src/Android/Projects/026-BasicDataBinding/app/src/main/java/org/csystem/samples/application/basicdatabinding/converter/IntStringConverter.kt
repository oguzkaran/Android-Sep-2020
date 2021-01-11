package org.csystem.samples.application.basicdatabinding.converter

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

object IntStringConverter {
    @InverseMethod("stringToInt")
    @JvmStatic
    fun intToString(value: Int) = value.toString()

    @JvmStatic
    fun stringToInt(str: String) : Int
    {
        var result = 1024

        try {
            result = str.toInt()
        }
        catch (ex: NumberFormatException) {

        }

        return result
    }
}