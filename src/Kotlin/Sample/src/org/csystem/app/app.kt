/*----------------------------------------------------------------------------------------------------------------------
    ProductFactory ve NameFactory sınıfları
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.app.samples.NameFactory
import org.csystem.app.samples.NumberFactory
import org.csystem.app.samples.ProductFactory

fun main()
{
    try {
        ProductFactory.loadFromFile("products.csv").forEach(::println)
        println()
        NameFactory.loadFromFile("names.csv").forEach(::println)
        println()
        NumberFactory.loadFromFile("numbers.csv").forEach(::println)
    }
    catch (ex: Throwable) {
        ex.printStackTrace()
    }
}