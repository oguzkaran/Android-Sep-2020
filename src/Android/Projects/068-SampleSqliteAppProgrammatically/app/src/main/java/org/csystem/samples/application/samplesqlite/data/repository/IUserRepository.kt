package org.csystem.samples.application.samplesqlite.data.repository

import org.csystem.samples.application.samplesqlite.data.entity.User
import org.csystem.util.data.repository.ICrudRepository

interface IUserRepository : ICrudRepository<User, Long> {
    fun  findUsersSortByRegisterDate() : Iterable<User>
}