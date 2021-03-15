/*----------------------------------------------------------------------------------------------------------------------
    Collections sınıfının synnchronizedXXX metotları collection'ları sarmalayan "thread-safe" collection
    nesneler elde edilebilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.util.readInt
import java.util.*
import kotlin.collections.ArrayList

fun main()
{
    val list = ArrayList<Int>()
    val sample = Sample(list)
    val n = readInt("Kaç thread yaratmak istiyorsunuz?")
    val threads = ArrayList<Thread>()

    for (i in 1..n)
        threads.add(Thread{sample.threadProc()}.apply { start() })

    for (t in threads)
        t.join()

    println("Size:${list.size}")
}

class Sample(intList: MutableList<Int>) {
    private val mIntList = Collections.synchronizedList(intList)

    fun threadProc()
    {
        for (i in 1..1_000_000)
            mIntList.add(i)
    }
}

