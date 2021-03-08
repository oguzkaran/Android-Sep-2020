/*----------------------------------------------------------------------------------------------------------------------
    Yukarıdaki problem aşağıdaki gibi WeakReference sınıfı kullanılarak çözülebilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import java.lang.ref.WeakReference

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
    val a = A(10)

    a.bar()
}

class A(private var a: Int) {
    private lateinit var mB: B

    private class B(a: A) {
        private val mWeakReference: WeakReference<A> = WeakReference(a)

        fun foo()
        {
            println("a = ${mWeakReference.get()?.a}")
        }
    }

    //...

    fun bar()
    {
        mB = B(this)
        mB.foo()
    }
}

