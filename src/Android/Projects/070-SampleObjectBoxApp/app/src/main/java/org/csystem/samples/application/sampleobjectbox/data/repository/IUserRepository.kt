package org.csystem.samples.application.sampleobjectbox.data.repository

import org.csystem.samples.application.sampleobjectbox.data.entity.User
import org.csystem.util.data.repository.ICrudRepository
import java.util.*

interface IUserRepository : ICrudRepository<User, Long> {
    fun  findUsersSortByRegisterDate() : MutableIterable<User>
    fun findUserByUsername(username: String) : Optional<User>
    fun findUsersByUsernameContains(text: String) : MutableIterable<User>
    fun existsByUsername(username: String) : Boolean
    fun deleteAllUsers() : Int
}