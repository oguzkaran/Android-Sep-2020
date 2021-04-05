/*----------------------------------------------------------------------------------------------------------------------
    Circle sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util.math.geometry

open class Circle(radius: Double = 0.0) {
    var r: Double= Math.abs(radius)
        set(value) {field = Math.abs(value)}

    val area: Double
        get() = Math.PI * r * r

    val circumference : Double
        get() = 2 * Math.PI * r
}