package org.csystem.samples.application.generic.helper

import org.csystem.samples.application.generic.randomgenerator.RandomGenerator
import javax.inject.Inject

class RandomGeneratorHelper @Inject constructor(val randomGenerator: RandomGenerator) {
    fun getRandomNumber(min: Int, max: Int) = randomGenerator.generateRandomNumber(min, max)
}