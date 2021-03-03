/*----------------------------------------------------------------------------------------------------------------------
    Yukarıdaki problem aşağıdaki gibi Closeable arayüzü ile çözülebilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import java.io.Closeable

fun main()
{
    for (i in 1..10)
        doWork()

    while (true) {
        for (i in 1..10)
            doWork()
    }
}

fun doWork()
{
    A(10).use {
        it.bar();
    }
}

class A(private var a: Int) : Closeable {
    private var mB: B? = null

    private inner class B {
        fun foo()
        {
            println("a = ${a}")
        }
    }

    //...

    fun bar()
    {
        mB = this.B()
        mB?.foo()
    }

    override fun close()
    {
        mB = null
    }
}
