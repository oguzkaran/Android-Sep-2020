/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örneği çalıştırarak sonucun neredeyse hiçbir zaman 2000000 elde edilemeyeceğini gözlemleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

fun main()
{
    val sample = Sample()
    val thread1 = Thread{ sample.threadProc1()}.apply { start() }
    val thread2 = Thread{ sample.threadProc1()}.apply { start() }
    val thread3 = Thread{ sample.threadProc2()}.apply { start() }

    thread1.join()
    thread2.join()
    thread3.join()

    println("Size:${sample.size}")
}

class Sample {
    private val mIntList: MutableList<Int> = ArrayList()

    val size: Int
        get() = mIntList.size

    fun threadProc1()
    {
        for (i in 1..1_000_000)
            mIntList.add(i)
    }

    fun threadProc2()
    {
        for (i in 1..1_000_000)
            mIntList.add(i * 10)
    }

    fun threadProc3()
    {
        for (i in 1..1_000_000)
            mIntList.add(i * 10)
    }
}

