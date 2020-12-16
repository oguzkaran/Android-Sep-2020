/*----------------------------------------------------------------------------------------------------------------------
    PointF sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util.math.geometry

import kotlin.math.sqrt

data class PointF(var x: Float = 0F, var y: Float = 0F) {
    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())
    constructor(x: Long, y: Long) : this(x.toFloat(), y.toFloat())

    fun distance(p: PointF) = distance(p.x, p.y)

    fun distance(x: Int, y: Int) = distance(x.toFloat(), y.toFloat())
    fun distance(x: Float = 0F, y: Float = 0F)
            = sqrt((this.x - x.toDouble()) * (this.x - x.toDouble()) + (this.y - y.toDouble()) * (this.y - y.toDouble()))

    fun offset(dx: Float, dy: Float = dx)
    {
        x += dx
        y += dy
    }
}