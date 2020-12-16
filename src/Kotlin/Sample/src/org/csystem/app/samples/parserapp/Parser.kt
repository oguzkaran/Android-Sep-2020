package org.csystem.app.samples.parserapp

class Parser(var source: Source) {
    fun parse()
    {
        var count = 0

        while (true) {
            val ch = source.nextChar;
            if (ch == -1)
                break;

            if (ch.toChar().isWhitespace())
                ++count
        }

        println("Count:${count}")
    }
}