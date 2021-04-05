package org.csystem.app.samples.parserapp

fun runParserApp()
{
    FileSource("test.txt").use {
        val parser = Parser(it)

        parser.parse()
    }

    println("Tekrar yapıyor musunuz?")
}