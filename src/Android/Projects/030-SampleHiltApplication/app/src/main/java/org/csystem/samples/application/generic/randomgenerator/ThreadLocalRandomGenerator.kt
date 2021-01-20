package org.csystem.samples.application.generic.randomgenerator

import java.util.concurrent.ThreadLocalRandom
import javax.inject.Inject

class ThreadLocalRandomGenerator @Inject constructor() {
    private val mThreadLocalRandom = ThreadLocalRandom.current()
    fun generateRandomNumber(min:Int, max: Int) = mThreadLocalRandom.nextInt(min, max)
}