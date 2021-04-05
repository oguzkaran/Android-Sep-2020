package org.csystem.app.samples.parserapp

import java.io.Closeable
import java.nio.file.Files
import java.nio.file.Paths

class FileSource(filename: String) : Source(), Closeable {
    private val mBufferedReader = Files.newBufferedReader(Paths.get(filename))

    override val nextChar: Int
        get() = mBufferedReader.read()

    override fun close()
    {
        mBufferedReader.close()
    }
}