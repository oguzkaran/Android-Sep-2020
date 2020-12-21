/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte birden fazla formatter ile işlem yapan örnek bir fonksiyon yazılmıştır. Detaylar gözardı edilmiştir.
    Bir kütüphane içerisine daha detaylısı eklenecektir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


fun tryParse(str: String) : LocalDate?
{
    val formatters = arrayOf(DateTimeFormatter.ofPattern("dd-MM-yyyy"), DateTimeFormatter.ofPattern("dd/MM/yyyy"), DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    for (formatter in formatters) {
        try {
            return LocalDate.parse(str, formatter)
        }
        catch (ex: DateTimeParseException) {

        }
    }
    return null
}

fun main()
{
    val str1 = "10/09/1976"
    val str2 = "10-09-1976"
    val str3 = "1976-09-10"

    println(tryParse(str1))
    println(tryParse(str2))
    println(tryParse(str3))
}
