package org.csystem.samples.application.generic.helper

import org.csystem.samples.application.generic.randomgenerator.RandomGenerator
import org.csystem.samples.application.generic.randomgenerator.ThreadLocalRandomGenerator
import java.util.concurrent.ThreadLocalRandom
import javax.inject.Inject

class RandomGeneratorHelper @Inject constructor(val randomGenerator: RandomGenerator,
                                                val threadLocalRandomGenerator: ThreadLocalRandomGenerator) {
    fun getRandomNumber(min: Int, max: Int) = randomGenerator.generateRandomNumber(min, max)
    fun getThreadLocalRandomNumber(min: Int, max: Int) = threadLocalRandomGenerator.generateRandomNumber(min, max)
}