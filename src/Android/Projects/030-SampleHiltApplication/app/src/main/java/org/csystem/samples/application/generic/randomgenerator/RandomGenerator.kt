package org.csystem.samples.application.generic.randomgenerator

import kotlin.random.Random
import javax.inject.Inject

class RandomGenerator @Inject constructor() {
    //...
    fun generateRandomNumber(min:Int, max: Int) = Random.nextInt(min, max)
}