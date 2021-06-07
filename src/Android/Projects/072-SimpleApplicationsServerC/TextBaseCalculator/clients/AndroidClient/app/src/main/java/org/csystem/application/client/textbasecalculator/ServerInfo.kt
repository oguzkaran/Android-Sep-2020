package org.csystem.application.client.textbasecalculator

const val MIN_PORT = 1024
const val MAX_PORT = 65535

data class ServerInfo(var host: String, var port: Int) {
    init {
        if (port < MIN_PORT || port > MAX_PORT)
            throw IllegalAccessException("Port number must be between $MIN_PORT and $MAX_PORT")
    }
}