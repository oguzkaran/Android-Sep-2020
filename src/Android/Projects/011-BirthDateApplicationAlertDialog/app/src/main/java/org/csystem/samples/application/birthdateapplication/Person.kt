package org.csystem.samples.application.birthdateapplication

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Person(var name: String, var birthDate: LocalDate) {
    val age: Double
        get() = ChronoUnit.DAYS.between(birthDate, LocalDate.now()) / 365.0
}