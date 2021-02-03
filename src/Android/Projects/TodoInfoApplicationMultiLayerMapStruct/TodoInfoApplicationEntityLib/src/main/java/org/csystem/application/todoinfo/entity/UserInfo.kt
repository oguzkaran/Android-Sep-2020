package org.csystem.application.todoinfo.entity

import java.io.Serializable
import java.time.LocalDateTime

data class UserInfo(var id: Long = 0, var username: String = "", var name: String = "",
                    var registerDate: LocalDateTime = LocalDateTime.now()) : Serializable