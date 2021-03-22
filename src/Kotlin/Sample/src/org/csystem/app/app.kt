/*----------------------------------------------------------------------------------------------------------------------
    fixed thread havuzları belirli bir sayıda thread'i çalıştırmak için kullanılmaktadır. n tane thread'den oluşan
    bir fixed thread havuzunda n adet thread de çalışır durumdaysa, n + 1-inci thread bekler ve ilk boşalan thread
    ile çalıştırılır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.util.readInt
import java.util.concurrent.Executors
import kotlin.random.Random

fun main()
{
    val m = readInt("Kaç tane thread yaratılsın?")
    val threadPool = Executors.newCachedThreadPool()

    for (i in 1..m)
        threadPool.submit{ threadProc("t$i")}

    threadPool.shutdown()
}

fun threadProc(str: String)
{
    val name = Thread.currentThread().name

    for (i in 1..5) {
        println("$str:$name:${Random.nextInt(100)}")
        Thread.sleep(1000)
    }
    println()
}


