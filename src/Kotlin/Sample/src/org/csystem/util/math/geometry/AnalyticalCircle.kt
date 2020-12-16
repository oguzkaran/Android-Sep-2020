/*----------------------------------------------------------------------------------------------------------------------
    AnalyticalCircle sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util.math.geometry

open class AnalyticalCircle(radius: Double = 0.0, x: Int = 0, y: Int = 0) : Circle(radius) {
    private val mCenter: Point = Point(x, y)

    constructor(radius: Double = 0.0, center: Point = Point()) : this(radius, center.x, center.y)

    var x: Int
        get() = mCenter.x
        set(value) { mCenter.x = value }

    var y: Int
        get() = mCenter.y
        set(value) { mCenter.y = value }

    var center: Point
        get() = Point(mCenter.x, mCenter.y)
        set(value) {setCenter(value.x, value.y)}

    fun setCenter(x: Int, y: Int)
    {
        this.x = x;
        this.y = y
    }

    fun offset(dx: Int, dy: Int = dx) = mCenter.offset(dx, dy)

    fun radiusDistance(analyticalCircle: AnalyticalCircle) = mCenter.distance(analyticalCircle.mCenter)
}