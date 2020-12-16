package org.csystem.util

fun String.isPangram(alphabet: String) : Boolean
{
    for (c in alphabet)
        if (!this.contains(c, ignoreCase = true))
            return false

    return true
}

fun String.isIsogram(alphabet: String) : Boolean
{
    for (c in alphabet)
        if (!this.isUnique(c))
            return false

    return true;
}

fun String.isPangramTR() = this.isPangram("abcçdefgğhıijklmnoöprsştuüvyz")
fun String.isPangramEN() = this.isPangram("abcdefghijklmnopqrstuwxvyz")

fun String.isUnique(ch: Char) : Boolean
{
    var count = 0
    val sch = ch.toLowerCase()

    for (c in this) {
        if (count > 1)
            return false

        if (c.toLowerCase() == sch)
            ++count
    }

    return count == 1
}


fun String.isIsogramTR() = this.isIsogram("abcçdefgğhıijklmnoöprsştuüvyz")
fun String.isIsogramEN(text: String) = this.isIsogram("abcdefghijklmnopqrstuwxvyz")

