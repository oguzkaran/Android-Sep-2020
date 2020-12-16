package org.csystem.util

class HashSetWithStatus<T> : HashSet<T>() {
    var falseCount : Int = 0
        private set
    val totalValuesCount : Int
       get() = this.count() + falseCount

    override fun add(element: T): Boolean
    {
        val result = super.add(element)

        if (!result)
            ++falseCount

        return result
    }

    //...
}