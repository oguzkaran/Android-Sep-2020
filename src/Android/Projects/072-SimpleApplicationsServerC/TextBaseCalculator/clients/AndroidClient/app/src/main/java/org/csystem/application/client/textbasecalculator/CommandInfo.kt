package org.csystem.application.client.textbasecalculator

data class CommandInfo(var text: String, var symbol: Char, var numberOfOperands: Int) {
    override fun toString() = symbol + ""
}