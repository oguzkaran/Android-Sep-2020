package org.csystem.app.samples.parserapp

class StringSource(private val text: String) : Source() {
    private var mIndex = 0

    override val nextChar: Int
        get() = if (mIndex == text.length) -1 else text[mIndex++].toInt()
}