package org.csystem.application.todoinfo.dal.helper

import org.csystem.application.todoinfo.entity.TodoInfo
import org.csystem.application.todoinfo.entity.UserInfo
import org.csystem.application.todoinfo.repository.ITodoInfoRepository
import org.csystem.application.todoinfo.repository.IUserInfoRepository
import org.csystem.util.data.DatabaseUtil as DB
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class TodoInfoApplicationDBHelper @Inject constructor(
                        private val mUserInfoRepository: IUserInfoRepository,
                        private val mTodoInfoRepository: ITodoInfoRepository) {

    fun findAllTodos() : Iterable<TodoInfo> = DB.doWorkForRepository("TodoInfoApplicationDBHelper.findAllTodos") {mTodoInfoRepository.findAll()}

    fun findAllUsers() : Iterable<UserInfo> = DB.doWorkForRepository("TodoInfoApplicationDBHelper.findAllUsers") {mUserInfoRepository.findAll()}

    fun findUserByUsername(username: String) : Optional<UserInfo>
    {
        //...
        return DB.doWorkForRepository("TodoInfoApplicationDBHelper.findUserByUsername") {mUserInfoRepository.findByUsername(username)}
    }

    fun findTodoByStartDate(startDate: LocalDate) : Iterable<TodoInfo>
    {
        return DB.doWorkForRepository("TodoInfoApplicationDBHelper.findTodoByStartDate") {mTodoInfoRepository.findByStartDate(startDate)}
    }

    fun saveUserInfo(userInfo: UserInfo?) : UserInfo?
    {
        return DB.doWorkForRepository("TodoInfoApplicationDBHelper.saveUserInfo") {mUserInfoRepository.save(userInfo)}
    }

    fun saveTodoInfo(todoInfo: TodoInfo) : TodoInfo?
    {
        return DB.doWorkForRepository("TodoInfoApplicationDBHelper.saveTodoInfo") {mTodoInfoRepository.save(todoInfo)}
    }
}