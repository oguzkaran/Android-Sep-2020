package org.csystem.samples.application.samplesqlite.data.dal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.csystem.samples.application.samplesqlite.data.entity.User
import org.csystem.samples.application.samplesqlite.data.repository.UserRepository
import org.csystem.samples.application.samplesqlite.data.util.AndroidIOUtil
import org.csystem.util.data.repository.RepositoryException
import java.io.Closeable
import java.util.*

class SampleSqLiteAppDAL(private val context: Context) : Closeable {
    companion object {
        private const val DATABASE_NAME = "samplesqliteappdb"
        private const val DATABASE_VERSION = 2
    }

    private val mDatabaseHelper = DatabaseHelper(context, DATABASE_NAME, DATABASE_VERSION)
    private lateinit var msSQLiteDatabase: SQLiteDatabase

    private class DatabaseHelper(
        context: Context, name: String, version: Int)
        : SQLiteOpenHelper(context, name, null, version) {
        override fun onCreate(db: SQLiteDatabase?) {
            try {

            }
            catch (ex: Throwable) {
                throw RepositoryException("DatabaseHelper.close", ex)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int)
        {
            try {

            }
            catch (ex: Throwable) {
                throw RepositoryException("DatabaseHelper.close", ex)
            }
        }
    }

    public fun open() :  SampleSqLiteAppDAL
    {
        AndroidIOUtil.copyDbIfNotExists(context, DATABASE_NAME, 1024)
        msSQLiteDatabase = mDatabaseHelper.writableDatabase
        UserRepository.db = msSQLiteDatabase

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
            msSQLiteDatabase.close()
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.close", ex)
        }
    }
}