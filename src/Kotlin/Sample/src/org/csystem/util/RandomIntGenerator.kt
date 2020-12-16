/*----------------------------------------------------------------------------------------------------------------------
    RandomIntGenerator sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util

import kotlin.random.Random

class RandomIntGenerator(val n: Int, val min: Int, val max: Int, val random: Random = Random) : Iterable<Int> {
    override fun iterator(): Iterator<Int>
    {
        return object : Iterator<Int> {
            var count = -1

            override fun hasNext(): Boolean
            {
                return count + 1 < n
            }

            override fun next(): Int
            {
                if (!hasNext())
                    throw NoSuchElementException("No such random data")

                ++count
                return random.nextInt(min, max)
            }
        }
    }
}