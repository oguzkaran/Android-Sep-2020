package org.csystem.app.samples.secondorderequation

import org.csystem.util.math.getRoots
import org.csystem.util.readDouble

private data class CoefficientInfo(val a: Double, val b: Double, val c: Double)

private fun getRootsByStandardInput() : CoefficientInfo
{
    val a = readDouble("a?")
    val b = readDouble("b?")
    val c = readDouble("c?")

    return CoefficientInfo( a,  b, c)
}

fun runSecondOrderEquationApp()
{
    while (true) {
        val (a, b, c) = getRootsByStandardInput()
        val (x1, x2) = getRoots(a, b, c)

        when {
            !x1.isNaN() -> println("x1=$x1, x2=$x2")
            else -> {
                println("Gerçek kök yok")
                break
            }
        }
    }
}