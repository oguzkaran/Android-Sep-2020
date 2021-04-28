package org.csystem.samples.application.samplesqlite.data.entity

import java.io.Serializable
import java.time.LocalDateTime

data class User(var userId : Long = 0,
                var name: String = "",
                var username: String = "",
                var password: String = "",
                var registerDate: LocalDateTime = LocalDateTime.now()) {

    override fun toString() = username
}