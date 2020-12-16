/*----------------------------------------------------------------------------------------------------------------------
    Complex sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util.math

private fun plus(re1: Double, im1: Double, re2: Double, im2: Double) = Complex(re1 + re2, im1 + im2)
private fun minus(re1: Double, im1: Double, re2: Double, im2: Double) = plus(re1, im1, -re2, -im2)

infix operator fun Complex.plus(complex: Complex) = this + complex
infix operator fun Complex.minus(complex: Complex) = this - complex

infix operator fun Double.plus(complex: Complex) = plus(this, 0.0, complex.re, complex.im)
infix operator fun Double.minus(complex: Complex) = minus(this, 0.0, complex.re, complex.im)

data class Complex(var re: Double = 0.0, var im: Double = 0.0) {
    val norm: Double
        get() = Math.sqrt(re * re + im * im)

    constructor(re: Int, im: Int) : this(re.toDouble(), im.toDouble())

    operator fun component3() = norm

    operator fun dec() = Complex(this.re - 1, this.im)

    operator fun inc() = Complex(this.re + 1, this.im)

    operator fun invoke() = toString()

    operator fun invoke(re: Double, im: Double)
    {
        this.re = re
        this.im = im
    }

    operator fun invoke(re: Int, im: Int) = this(re.toDouble(), im.toDouble())

    operator fun unaryMinus() = Complex(-this.re, -this.im)

    operator fun unaryPlus() = this//Complex(this.re, this.im)

    operator fun plus(complex: Complex) = plus(this.re, this.im, complex.re, complex.im)
    operator fun plus(value: Double) = plus(this.re, this.im, value, 0.0)

    operator fun minus(complex: Complex) = minus(this.re, this.im, complex.re, complex.im)
    operator fun minus(value: Double) = minus(this.re, this.im, value, 0.0)

    override fun toString() = "|$re + i.$im|=$norm"
}