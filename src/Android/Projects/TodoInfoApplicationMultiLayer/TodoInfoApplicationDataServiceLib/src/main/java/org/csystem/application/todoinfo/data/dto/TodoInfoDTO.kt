package org.csystem.application.todoinfo.data.dto


import java.io.Serializable
import java.time.LocalDate

data class TodoInfoDTO(var id: Long = 0,
                       var title: String = "",
                       var description: String = "",
                       var startDate: LocalDate = LocalDate.now(),
                       var expectedEndDate: LocalDate = startDate.plusDays(10),
                       var completed: Boolean = false) : Serializable {
    override fun toString() = title
}









