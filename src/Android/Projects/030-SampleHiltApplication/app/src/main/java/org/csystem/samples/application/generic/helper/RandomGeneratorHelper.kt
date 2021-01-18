package org.csystem.samples.application.generic.helper

import org.csystem.samples.application.generic.randomgenerator.RandomGenerator
import org.csystem.samples.application.generic.randomgenerator.RandomWithSeedGenerator
import org.csystem.samples.application.generic.randomgenerator.ThreadLocalRandomGenerator
import org.csystem.samples.application.generic.randomgenerator.ThreadLocalRandomInjectGenerator
import javax.inject.Inject

class RandomGeneratorHelper @Inject constructor(
        private val randomGenerator: RandomGenerator,
        private val randomWithSeedGenerator: RandomWithSeedGenerator,
        private val threadLocalRandomGenerator: ThreadLocalRandomGenerator,
        private val threadLocalInjectRandomGenerator: ThreadLocalRandomInjectGenerator,
        ) {
    fun getRandomNumber(min: Int, max: Int) = randomGenerator.generateRandomNumber(min, max)
    fun getRandomWithSeedNumber(min: Int, max: Int) = randomWithSeedGenerator.generateRandomNumber(min, max)
    fun getThreadLocalRandomNumber(min: Int, max: Int) = threadLocalRandomGenerator.generateRandomNumber(min, max)
    fun getThreadLocalInjectRandomNumber(min: Int, max: Int) = threadLocalInjectRandomGenerator.generateRandomNumber(min, max)
}