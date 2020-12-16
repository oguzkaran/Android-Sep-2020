package org.csystem.util.math

import kotlin.math.sqrt

data class SecondOrderEquationRoot(val x1: Double, val x2: Double)

private fun delta(a: Double, b: Double, c: Double) = b * b - 4 * a * c

fun getRoots(a: Double, b: Double, c: Double) : SecondOrderEquationRoot
{
    val delta = delta(a, b, c)

    fun doWorkForNonnegativeDelta() : SecondOrderEquationRoot
    {
        val sqrtDelta = sqrt(delta)
        val x1 = (-b + sqrtDelta) / (2 * a)
        val x2 = (-b - sqrtDelta) / (2 * a)

        return SecondOrderEquationRoot(x1, x2)
    }

    fun doWorkForNegativeDelta() = SecondOrderEquationRoot(Double.NaN, Double.NaN)

    return when  {
        delta >= 0.0 -> doWorkForNonnegativeDelta()
        else -> doWorkForNegativeDelta()
    }
}