package org.csystem.util

import kotlin.random.Random

fun IntArray.display(n: Int = 1)
{
    for (value in this)
        print("%%0%dd ".format(n).format(value))

    println()
}

fun DoubleArray.display(n: Int = 0)
{
    val format = if (n == 0) "%f" else "%%.%df".format(n)

    for (value in this)
        println(format.format(value))
}

fun Random.randomIntArray(n: Int, min: Int, max: Int) : IntArray //[min, max)
{
    val a = IntArray(n)

    for (i in 0 until n)
        a[i] = this.nextInt(min, max)

    return a
}

fun Random.randomDoubleArray(n: Int, min: Double, max: Double) : DoubleArray //[min, max)
{
    val a = DoubleArray(n)

    for (i in 0 until n)
        a[i] = this.nextDouble(min, max)

    return a
}