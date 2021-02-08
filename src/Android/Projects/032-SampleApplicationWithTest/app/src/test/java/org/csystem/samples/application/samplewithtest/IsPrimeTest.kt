package org.csystem.samples.application.samplewithtest

import org.csystem.util.NumberUtil
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.nio.file.Files
import java.nio.file.Paths

@RunWith(Parameterized::class)
class IsPrimeTest(private val mDataInfo: DataInfo) {
    class DataInfo(val a: Int, val result: Boolean)

    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun provideData(): MutableCollection<DataInfo>
        {
            val data = ArrayList<DataInfo>()

            Files.newBufferedReader(Paths.get("setup-prime.txt")).useLines {
                it.map { it.split(' ') }.map {DataInfo(it[0].toInt(), it[1].toBoolean())}.forEach { data.add(it) }
            }

            return data
        }
    }

    @Test
    fun testIsPrime()
    {
        assertEquals(mDataInfo.result, NumberUtil.isPrime(mDataInfo.a))
    }
}