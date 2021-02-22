package org.csystem.samples.application.todolist.model

import java.io.Serializable
import java.time.LocalDateTime

data class Todo(var title: String = "",
                var description: String = "",
                var lastUpdate: LocalDateTime = LocalDateTime.now()) : Serializable {
    override fun toString(): String = title
}