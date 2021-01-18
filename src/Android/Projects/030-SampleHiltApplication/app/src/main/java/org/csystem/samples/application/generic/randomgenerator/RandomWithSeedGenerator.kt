package org.csystem.samples.application.generic.randomgenerator

import org.csystem.samples.application.generic.annotation.provider.AuthRandomWithSeed
import javax.inject.Inject
import kotlin.random.Random

class RandomWithSeedGenerator @Inject constructor(@AuthRandomWithSeed private val mRandom: Random) {
    //...
    fun generateRandomNumber(min:Int, max: Int) = mRandom.nextInt(min, max)
}