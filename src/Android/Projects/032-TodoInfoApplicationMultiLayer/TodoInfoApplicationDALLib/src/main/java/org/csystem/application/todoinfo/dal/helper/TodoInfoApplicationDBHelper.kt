package org.csystem.application.todoinfo.dal.helper

import org.csystem.application.todoinfo.entity.TodoInfo
import org.csystem.application.todoinfo.entity.UserInfo
import org.csystem.application.todoinfo.repository.ITodoInfoRepository
import org.csystem.application.todoinfo.repository.IUserInfoRepository
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class TodoInfoApplicationDBHelper @Inject constructor(
                        private val mUserInfoRepository: IUserInfoRepository,
                        private val mTodoInfoRepository: ITodoInfoRepository) {

    fun findAllTodos() : Iterable<TodoInfo>
    {
        return mTodoInfoRepository.findAll()
    }

    fun findAllUsers() : Iterable<UserInfo>
    {
        return mUserInfoRepository.findAll()
    }

    fun findUserByUsername(username: String) : Optional<UserInfo>
    {
        return mUserInfoRepository.findByUsername(username)
    }

    fun findTodoByStartDate(startDate: LocalDate) : Iterable<TodoInfo>
    {
        return mTodoInfoRepository.findByStartDate(startDate)
    }

    fun saveUserInfo(userInfo: UserInfo?) : UserInfo?
    {
        return mUserInfoRepository.save(userInfo)
    }

    fun saveTodoInfo(todoInfo: TodoInfo) : TodoInfo?
    {
        return mTodoInfoRepository.save(todoInfo)
    }


}