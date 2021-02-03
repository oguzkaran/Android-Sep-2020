package org.csystem.application.todoinfo.repository

import org.csystem.application.todoinfo.entity.UserInfo
import org.csystem.util.data.repository.ICrudRepository
import java.util.*

interface IUserInfoRepository : ICrudRepository<UserInfo, Long> {
    fun findByUsername(username: String) : Optional<UserInfo>
    //...
}