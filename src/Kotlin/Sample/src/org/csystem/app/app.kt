/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte bir thread arka planda asenkron olarak kelimeler üretip bir listede toplamış bu listeyi
    döndürmüştür
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.util.readInt
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.collections.ArrayList
import kotlin.random.Random

fun main()
{
    val threadExecutor = Executors.newSingleThreadExecutor()

    while (true) {
        val n = readInt("Bir sayı giriniz:")
        if (n == 0)
            break
        val minLength = readInt("Minimum uzunluk?")
        val maxLength = readInt("Maximum uzunluk?")
        val timer = Timer()
        val words = threadExecutor.submit(Callable{ randomTextGeneratorCallback(timer, n, minLength, maxLength) }).get()

        timer.cancel()
        println("\n${words.joinToString(separator = "-")}")
        println("////////////////////")
    }

    threadExecutor.shutdown()
}

fun generateRandomStringEN(n: Int) : String
{
    val sb = StringBuilder(n)

    fun getChar() = when (Random.nextBoolean()) {
        true -> Random.nextInt('A'.toInt(), ('Z' + 1).toInt()).toChar()
        else -> Random.nextInt('a'.toInt(), ('z' + 1).toInt()).toChar()
    }
    for (i in 1..n)
        sb.append(getChar())

    return sb.toString()
}

fun randomTextGeneratorCallback(timer: Timer, n: Int, textMinLength: Int, textMaxLength: Int) =
        ArrayList<String>().apply {
            timer.schedule(object: TimerTask() {
                override fun run() = print(".")
            }, 0, 1000)
            for (i in 1..n) {
                this.add(generateRandomStringEN(Random.nextInt(textMinLength, textMaxLength)))
                Thread.sleep(Random.nextLong(2000))
            }
        }


