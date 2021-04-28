package org.csystem.samples.application.sampleobjectbox.data.dal

import android.content.Context
import org.csystem.samples.application.sampleobjectbox.data.entity.User
import org.csystem.samples.application.sampleobjectbox.data.repository.UserRepository
import org.csystem.util.data.repository.RepositoryException
import java.io.Closeable
import java.util.*

class SampleObjectBoxAppDAL(private val context: Context) : Closeable {
    public fun open() :  SampleObjectBoxAppDAL
    {
        return this;
    }

    fun saveUser(user: User) : User
    {
        try {
            return UserRepository.save(user)
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.saveUser", ex)
        }
    }

    fun findAll() : MutableIterable<User>
    {
        try {
            return UserRepository.findAll()
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.findAll", ex)
        }
    }

    fun findUsersSortByRegisterDate() : MutableIterable<User>
    {
        try {
            return UserRepository.findUsersSortByRegisterDate()
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.findUsersSortByRegisterDate", ex)
        }
    }

    fun delete(user: User)
    {
        try {
            UserRepository.delete(user)
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.delete", ex)
        }
    }

    fun deleteById(id: Long)
    {
        try {
            UserRepository.deleteById(id)
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.deleteById", ex)
        }
    }

    fun deleteAll()
    {
        try {
            UserRepository.deleteAll()
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.deleteAll", ex)
        }
    }

    fun deleteAllUsers() : Int
    {
        try {
            return UserRepository.deleteAllUsers()
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.deleteAllUsers", ex)
        }
    }

    fun findUserByUsername(username: String) : Optional<User>
    {
        try {
            return UserRepository.findUserByUsername(username)
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.findUserByUsername", ex)
        }
    }

    fun findUsersByUsernameContains(text: String) : MutableIterable<User>
    {
        try {
            return UserRepository.findUsersByUsernameContains(text)
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.findUsersByUsernameContains", ex)
        }
    }


    fun existsByUsername(username: String) : Boolean
    {
        try {
            return UserRepository.existsByUsername(username)
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.existsByUsername", ex)
        }
    }

    override fun close()
    {
        try {
            //...
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.close", ex)
        }
    }
}