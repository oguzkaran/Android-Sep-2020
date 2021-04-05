/*----------------------------------------------------------------------------------------------------------------------
    Yukarıdaki problemin Semaphore kullanarak kuyruklu çözümü
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import java.util.concurrent.Semaphore
import kotlin.random.Random

const val QUEUE_SIZE = 10

class ProducerConsumer {
    private var mQueue = IntArray(QUEUE_SIZE)
    private var mCount: Int = 0
    private val mProducerSemaphore = Semaphore(QUEUE_SIZE, true)
    private val mConsumerSemaphore = Semaphore(0)
    private var mHead = 0
    private var mTail = 0

    fun producerThreadProc()
    {
        do {
            mProducerSemaphore.acquire(mQueue.size)
            mQueue[mHead++] = mCount++
            mHead %= mQueue.size
            mConsumerSemaphore.release()
            Thread.sleep(Random.nextLong(0, 500))
        } while (mCount != 100)
    }

    fun consumerThreadProc()
    {
        do {
            mConsumerSemaphore.acquire()
            val value = mQueue[mTail++]
            mTail %= mQueue.size
            mProducerSemaphore.release(mQueue.size)
            print("$value ")
            Thread.sleep(Random.nextLong(0, 500))
        } while (value != 99)
    }

    fun run()
    {
        Thread{producerThreadProc()}.apply { start() }
        Thread{consumerThreadProc()}.apply { start() }
    }
}

fun main()
{
    val pc = ProducerConsumer()

    pc.run()
}




