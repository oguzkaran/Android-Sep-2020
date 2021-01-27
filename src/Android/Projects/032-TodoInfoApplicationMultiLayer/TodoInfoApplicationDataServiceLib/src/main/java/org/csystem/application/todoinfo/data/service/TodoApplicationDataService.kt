package org.csystem.application.todoinfo.data.service

import org.csystem.application.todoinfo.dal.helper.TodoInfoApplicationDBHelper
import org.csystem.application.todoinfo.data.converter.ITodoInfoDTOConverter
import org.csystem.application.todoinfo.data.dto.TodoInfoDTO
import org.csystem.application.todoinfo.entity.TodoInfo
import javax.inject.Inject
import org.csystem.util.data.DatabaseUtil as DB

class TodoApplicationDataService @Inject constructor(
    private val mTodoApplicationDataHelper: TodoInfoApplicationDBHelper,
    private val mTodoInfoDTOConverter: ITodoInfoDTOConverter
) {

    fun findAllTodos() : Iterable<TodoInfoDTO>
    {
        return DB.doWorkForService("TodoApplicationDataService.findAllTodos")
            {mTodoApplicationDataHelper.findAllTodos().map { mTodoInfoDTOConverter.todoToTodoInfoDTO(it) }}
    }


    fun saveTodo(todoInfoDTO: TodoInfoDTO) : TodoInfoDTO
    {
        return DB.doWorkForService("TodoApplicationDataService.findAllTodos")
            {
                mTodoApplicationDataHelper.saveTodoInfo(mTodoInfoDTOConverter.todoInfoDTOToTodoInfo(todoInfoDTO))
                todoInfoDTO
            }
    }

    //..
}