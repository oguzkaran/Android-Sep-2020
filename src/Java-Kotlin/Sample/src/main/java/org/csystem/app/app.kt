/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte coroutine içerisindeki fonksiyonun geri dönüş değeri elde edilmiştir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        doWork()
        println("Hello World")
        readLine()
    }
}

suspend fun doWork()
{
    GlobalScope.launch {
        getResult()
    }
}
suspend fun getResult() = coroutineScope {
    println("result = ${async(Dispatchers.Main) { foo() }.await()}")
    println("Hello...")
}
suspend fun foo() : Int
{
    delay(3000)
    return 10
}

