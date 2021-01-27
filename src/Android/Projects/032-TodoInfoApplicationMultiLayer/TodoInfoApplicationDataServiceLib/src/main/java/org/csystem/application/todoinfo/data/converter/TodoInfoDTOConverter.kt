package org.csystem.application.todoinfo.data.converter

import org.csystem.application.todoinfo.data.dto.TodoInfoDTO
import org.csystem.application.todoinfo.entity.TodoInfo
import java.time.LocalDate
import javax.inject.Inject

class TodoInfoDTOConverter @Inject constructor ():
    ITodoInfoDTOConverter {
    override fun todoToTodoInfoDTO(todoInfo: TodoInfo?): TodoInfoDTO
    {
        val todoInfoDTO = TodoInfoDTO()

        todoInfoDTO.id = todoInfo?.id!!
        todoInfoDTO.title = todoInfo.title
        todoInfoDTO.description = todoInfo.description
        todoInfoDTO.startDate = todoInfo.startDate
        todoInfoDTO.expectedEndDate = todoInfo.expectedEndDate
        todoInfoDTO.completed = todoInfo.completed

        return todoInfoDTO
    }

    override fun todoInfoDTOToTodoInfo(todoInfoDTO: TodoInfoDTO?): TodoInfo
    {
        val todoInfo = TodoInfo(startDate = LocalDate.now(), expectedEndDate = LocalDate.now())

        todoInfo.id = todoInfoDTO?.id!!
        todoInfo.title = todoInfoDTO.title
        todoInfo.description = todoInfoDTO.description
        todoInfo.startDate = todoInfoDTO.startDate
        todoInfo.expectedEndDate = todoInfoDTO.expectedEndDate
        todoInfo.completed = todoInfoDTO.completed

        return todoInfo
    }
}