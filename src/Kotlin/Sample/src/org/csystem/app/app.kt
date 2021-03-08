/*----------------------------------------------------------------------------------------------------------------------
    static interrupted metodu interrupt flag değerini reset eder. Aşağıdaki programı çalıştırarak durumu gözlemleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

fun main()
{
    val thread = Thread{ threadProc()}.apply { start() }

    Thread.sleep(5000)
    thread.interrupt()
}

fun threadProc()
{
    var i = 0

    val self = Thread.currentThread()

    while (!Thread.interrupted()) {
        print("${i++} ")
        //...
    }

    println()

    while (!self.isInterrupted) {
        println("İkinci döngü: ${i++}")
        Thread.sleep(1000)
        //...
    }

    println("Thread sonlanıyor!!!")
}