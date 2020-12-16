package org.csystem.app.samples

import org.csystem.util.readString

class ConsoleUtil {
    companion object {
        @JvmStatic
        fun readInt(message: String, errorMessage: String): Int {
            while (true) {
                try {
                    return readString(message).toInt()
                } catch (ex: Throwable) {
                    print(errorMessage)
                }
            }
        }
    }
}