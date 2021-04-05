package org.csystem.app.samples.parserapp

class CharArraySource(text: String) : Source() {
    private val mChars: CharArray = text.toCharArray()
    private var mIndex = 0

    override val nextChar: Int
        get() = if (mIndex == mChars.size) -1 else mChars[mIndex++].toInt()
}