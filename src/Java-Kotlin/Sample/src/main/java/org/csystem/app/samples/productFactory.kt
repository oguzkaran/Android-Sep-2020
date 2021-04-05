package org.csystem.app.samples

import java.nio.file.Files
import java.nio.file.Paths
import java.util.TreeSet
import kotlin.Comparator

private fun getProduct(line: String) : Product
{
    val productInfo = line.split(',')
    val id = productInfo[0].toInt()
    val name = productInfo.subList(1, productInfo.size - 3).joinToString(",")
    val stock = productInfo[productInfo.size - 3].toInt()
    val cost = productInfo[productInfo.size - 2].toBigDecimal()
    val price = productInfo[productInfo.size - 1].toBigDecimal()

    return Product(id, name, stock, cost, price)
}

fun loadProductsFromFile(path: String) : Iterable<Product> =
        Files.newBufferedReader(Paths.get(path)).useLines {
            it.toList().drop(1).map { getProduct(it)}
        }

fun loadProductsFromFileAsArray(path: String) = loadProductsFromFile(path).toList().toTypedArray()

fun loadProductsFromFileAsSet(path: String) = loadProductsFromFile(path).toSet()

fun loadProductsFromFileAsTreeSet(path: String) : TreeSet<Product> =
    TreeSet<Product>(Comparator.comparing { it.id }).apply {
        loadProductsFromFile(path).forEach{ this.add(it)}
    }


object ProductFactory {
    fun loadFromFile(path: String) = loadProductsFromFile(path)
    fun loadFromFileAsArray(path: String) = loadProductsFromFileAsArray(path)
    fun loadFromFileAsSet(path: String) = loadProductsFromFileAsSet(path)
    fun loadFromFileAsTreeSet(path: String) = loadProductsFromFileAsTreeSet(path)
}


