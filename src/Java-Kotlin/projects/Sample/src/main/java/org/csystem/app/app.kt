/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte bir couritine içerisinde başka bir coroutine yaratılmış, ve runBlocking ile yaratılan
    couroutine doWork içerisinde yaratılan coroutine'i beklemektedir. Bu durumda derleyici ve "Virtual machine" optimize
    ederek couroutine sayısını düşürebilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.csystem.util.readInt
import kotlin.random.Random

suspend fun main() = run().apply { println("run çağrıldı") }.join()

fun doWork()  = runBlocking {
    var sum = 0
    val n = readInt("Bir sayı giriniz:")

    val job = GlobalScope.launch {
        for (i in 1..n) {
            val value = Random.nextInt(100)
            print("$value ")
            sum += value
            delay(Random.nextLong(1, 1200))
        }
        println()
    }

    job.join()
    println("Toplam:$sum")
}

fun run() =
    GlobalScope.launch {
        doWork()
    }

