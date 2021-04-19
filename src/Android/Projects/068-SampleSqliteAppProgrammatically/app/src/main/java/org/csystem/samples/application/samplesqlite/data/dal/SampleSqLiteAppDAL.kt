package org.csystem.samples.application.samplesqlite.data.dal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.csystem.samples.application.samplesqlite.data.entity.User
import org.csystem.samples.application.samplesqlite.data.repository.UserRepository
import org.csystem.util.data.repository.RepositoryException
import java.io.Closeable

class SampleSqLiteAppDAL(context: Context) : Closeable {
    companion object {
        private const val DATABASE_NAME = "samplesqliteappdb.sqlite3"
        private const val DATABASE_VERSION = 1
    }

    private val mDatabaseHelper = DatabaseHelper(context, DATABASE_NAME, DATABASE_VERSION)
    private lateinit var msSQLiteDatabase: SQLiteDatabase

    private class DatabaseHelper(
        context: Context, name: String, version: Int)
        : SQLiteOpenHelper(context, name, null, version) {
        override fun onCreate(db: SQLiteDatabase?) {
            try {
                db?.execSQL(UserRepository.CREATE_TABLE)
            }
            catch (ex: Throwable) {
                throw RepositoryException("DatabaseHelper.close", ex)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int)
        {
            try {
                db?.execSQL("DROP TABLE IF EXISTS ${UserRepository.TABLE_NAME}")
                onCreate(db)
            }
            catch (ex: Throwable) {
                throw RepositoryException("DatabaseHelper.close", ex)
            }
        }
    }

    public fun open() :  SampleSqLiteAppDAL
    {
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

    fun findAll() : Iterable<User>
    {
        try {
            return UserRepository.findAll()
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.findAll", ex)
        }
    }

    fun findUsersSortByRegisterDate() : Iterable<User>
    {
        try {
            return UserRepository.findUsersSortByRegisterDate()
        }
        catch (ex: Throwable) {
            throw RepositoryException("SampleSqLiteAppDAL.findUsersSortByRegisterDate", ex)
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