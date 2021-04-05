package org.csystem.app.samples.randomanygenerator

import org.csystem.util.math.Complex
import org.csystem.util.math.geometry.Circle
import kotlin.random.Random

private fun getRandomObject(random: Random) : Any = when (random.nextInt(4)) {
    0 -> random.nextInt(100)
    1 -> Complex(random.nextDouble(3.4), random.nextDouble())
    2 -> Circle(random.nextDouble(6.544))
    else -> random.nextBoolean()
}

fun generateArray(n: Int, random: Random = Random) : Array<Any> =
        generateSequence{ getRandomObject(random)}.take(n).map { it as Any }.toList().toTypedArray()