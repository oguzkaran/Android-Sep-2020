package org.csystem.util

import java.math.BigDecimal

fun readString(message: String, end: String = "") : String
{
    print(message + end)

    return readLine()!!
}

fun readInt(message: String, errorMessage: String = "", end: String = "") : Int
{
    while (true) {
        try {
            return readString(message, end).toInt()
        }
        catch (ex: Throwable) {
            print(errorMessage + end)
        }
    }
}

fun readBigDecimal(message: String, errorMessage: String = "", end: String = "") : BigDecimal
{
    while (true) {
        try {
            return readString(message, end).toBigDecimal()
        }
        catch (ex: Throwable) {
            print(errorMessage + end)
        }
    }
}

fun readLong(message: String, errorMessage: String = "", end: String = "") : Long
{
    while (true) {
        try {
            return readString(message, end).toLong()
        }
        catch (ex: Throwable) {
            print(errorMessage + end)
        }
    }
}


fun readDouble(message: String, errorMessage: String = "", end: String = "") : Double
{
    while (true) {
        try {
            return readString(message, end).toDouble()
        }
        catch (ex: Throwable) {
            print(errorMessage + end)
        }
    }
}
