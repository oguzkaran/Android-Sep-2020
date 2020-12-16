package org.csystem.samples.application.simplepersoninfo.model.global

object PersonValidityUtil {
    private fun isValidLengthEquals(text: String, length: Int) = text.length == length
    private fun isValidLengthGreaterOrEquals(text: String, length: Int) = text.length >= length
    //...
    fun isValidCitizenId(text: String) = isValidLengthEquals(text, 11)
    fun isInvalidCitizenId(text: String) = !isValidCitizenId(text)
    fun isValidPhone(text: String) = isValidLengthGreaterOrEquals(text, 10)
    fun isInvalidPhone(text: String) = !isValidPhone(text)

    fun validate(name: String, citizenId: String, phone: String) : Boolean
    {
        if (name.isBlank()) {
            //...
            return false
        }

        if (citizenId.isBlank() || isInvalidCitizenId(citizenId)) {
            //...
            return false
        }

        if (phone.isBlank() || isInvalidPhone(phone)) {
            //
            return false
        }

        return true
    }
}