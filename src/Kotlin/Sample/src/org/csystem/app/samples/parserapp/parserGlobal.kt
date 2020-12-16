package org.csystem.app.samples.parserapp

fun parse(source: Source)
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