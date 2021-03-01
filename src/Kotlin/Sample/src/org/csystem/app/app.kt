/*----------------------------------------------------------------------------------------------------------------------
    Birden fazla thread için hangi thread'in önce çizelgelemeye gireceğinin bir garantisi yoktur. Aşağıdaki kodu
    çeşitli girişlerle çalıştırıp sonucu gözlemleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.util.readInt

fun main()
{
    val n = readInt("Kaç thread yaratmak istiyorsunuz?")

    for (i in 1..n)
        Thread{ threadProc()}.start()

    println("main ends!...")
}

fun threadProc()
{
    val thread = Thread.currentThread()

    for (i in 1..1_000_000)
        println("${thread.name}:$i")
}
