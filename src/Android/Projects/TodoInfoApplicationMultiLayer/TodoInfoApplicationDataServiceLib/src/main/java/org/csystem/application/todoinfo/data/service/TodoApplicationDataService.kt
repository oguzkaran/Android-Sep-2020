package org.csystem.application.todoinfo.data.service

import org.csystem.application.todoinfo.dal.helper.TodoInfoApplicationDBHelper
import org.csystem.application.todoinfo.data.converter.ITodoInfoDTOConverter
import org.csystem.application.todoinfo.data.dto.TodoInfoDTO
import org.mapstruct.factory.Mappers
import javax.inject.Inject
import org.csystem.util.data.DatabaseUtil as DB

class TodoApplicationDataService @Inject constructor(
    private val mTodoApplicationDataHelper: TodoInfoApplicationDBHelper) {

    private val todoInfoDTOConverter : ITodoInfoDTOConverter = Mappers.getMapper(ITodoInfoDTOConverter::class.java)

    fun findAllTodos() : Iterable<TodoInfoDTO>
    {
        return DB.doWorkForService("TodoApplicationDataService.findAllTodos")
            {mTodoApplicationDataHelper.findAllTodos().map { todoInfoDTOConverter.todoToTodoInfoDTO(it) }}
    }


    fun saveTodo(todoInfoDTO: TodoInfoDTO) : TodoInfoDTO
    {
        return DB.doWorkForService("TodoApplicationDataService.findAllTodos")
            {
                mTodoApplicationDataHelper.saveTodoInfo(todoInfoDTOConverter.todoInfoDTOToTodoInfo(todoInfoDTO))
                todoInfoDTO
            }
    }

    //..
}