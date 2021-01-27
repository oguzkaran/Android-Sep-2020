package org.csystem.application.todoinfo.entity

import java.io.Serializable
import java.time.LocalDate

data class TodoInfo(var id: Long = 0, var title: String = "", var description: String = "",
                    var startDate: LocalDate, var expectedEndDate: LocalDate,
                    var endDate: LocalDate? = null, var completed: Boolean = false) : Serializable