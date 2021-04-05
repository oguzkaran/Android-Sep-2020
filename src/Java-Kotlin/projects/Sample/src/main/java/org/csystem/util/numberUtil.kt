package org.csystem.util

import kotlin.math.absoluteValue
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt

private val onesTR = arrayOf("", "bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz", "dokuz")
private val tensTR = arrayOf("", "on", "yirmi", "otuz", "kırk", "elli", "altmış", "yetmiş", "seksen", "doksan")

private fun numToStr3DTR(value: Int): String
{
    if (value == 0)
        return "sıfır"

    val stringBuilder = StringBuilder(if (value < 0) "eksi" else "")
    val temp = value.absoluteValue
    val a = temp / 100
    val b = temp / 10 % 10
    val c = temp % 10

    if (a != 0) {
        if (a != 1)
            stringBuilder.append(onesTR[a])

        stringBuilder.append("yüz")
    }

    if (b != 0)
        stringBuilder.append(tensTR[b])

    if (c != 0)
        stringBuilder.append(onesTR[c])

    return stringBuilder.toString()
}

fun Int.digitsCount() = if (this == 0) 1 else log10(this.absoluteValue.toDouble()).toInt() + 1

fun Int.digits(n: Int = 1) = this.toLong().digits(n)

fun Long.digits(n: Int = 1) : IntArray
{
    val count = if (this == 0L) 1 else (log10(this.absoluteValue.toDouble()) / n ).toInt() + 1
    val result = IntArray(count)
    var temp = this.absoluteValue
    val divisor = 10.0.pow(n.toDouble()).toLong()

    for (i in result.indices.reversed()) {
        result[i] = (temp % divisor).toInt()
        temp /= divisor
    }

    return result
}

fun Long.digitsInTwos() = this.digits(2)

fun Int.digitsInTwos() = this.toLong().digitsInTwos()

fun Long.digitsInThrees() = this.digits(3)

fun Int.digitsInThrees() = this.toLong().digitsInThrees()

fun Int.numToStrTR() = this.toLong().numToStrTR()

fun Int.isPrime() : Boolean
{
    if (this <= 1)
        return false

    if (this % 2 == 0)
        return this == 2

    if (this % 3 == 0)
        return this == 3

    if (this % 5 == 0)
        return this == 5

    if (this % 7 == 0)
        return this == 7

    val sqrtValue = sqrt(this.toDouble())

    for (i in 11..sqrtValue.toInt())
        if (this % i == 0)
            return false

    return true
}

fun Long.numToStrTR() : String
{
    //...
    val valueArray = this.digitsInThrees()
    //...
    var str = numToStr3DTR(this.toInt());

    //...

    return str
}