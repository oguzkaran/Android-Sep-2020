/*----------------------------------------------------------------------------------------------------------------------
    Value sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util

import java.io.Serializable

data class Value<out T>(val value: T) : Serializable {
    fun toList() : List<T> = listOf(value)
}