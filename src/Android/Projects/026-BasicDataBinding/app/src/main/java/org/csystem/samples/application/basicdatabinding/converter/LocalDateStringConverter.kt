package org.csystem.samples.application.basicdatabinding.converter

import androidx.databinding.InverseMethod
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDateStringConverter {
    @JvmStatic
    private val mFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    @InverseMethod("stringToLocalDate")
    @JvmStatic
    fun localDateToString(date: LocalDate) = mFormatter.format(date)

    @JvmStatic
    fun stringToLocalDate(str: String) : LocalDate
    {
        var result = LocalDate.of(1970, 1, 1)

        try {
            result = LocalDate.parse(str, mFormatter)
        }
        catch (ex: DateTimeException) {

        }

        return result
    }
}