package org.csystem.samples.application.samplesqlite.data.repository

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import org.csystem.samples.application.samplesqlite.data.entity.User
import org.csystem.util.data.repository.ICrudRepository
import org.csystem.util.datetime.DateTimeUtil
import java.util.*
import kotlin.collections.ArrayList

object UserRepository : IUserRepository {
    private const val ID = "user_id"
    private const val NAME = "name"
    private const val USERNAME = "username"
    private const val PASSWORD = "password"
    private const val REGISTER_DATE = "register_date"

    const val TABLE_NAME = "users"

    private fun getContentValues(u: User?) : ContentValues
    {
        val contentValues = ContentValues();

        contentValues.put(NAME, u?.name)
        contentValues.put(USERNAME, u?.username)
        contentValues.put(PASSWORD, u?.password)
        contentValues.put(REGISTER_DATE, DateTimeUtil.toMilliseconds(u?.registerDate))

        return contentValues
    }

    private fun getUser(cursor: Cursor) : User
    {
        val userId = cursor.getLong(0)
        val name = cursor.getString(1)
        val username = cursor.getString(2)
        val password = cursor.getString(3)
        val registerDate = DateTimeUtil.toLocalDateTime(cursor.getLong(4))

        return User(userId, name, username, password, registerDate)
    }

    lateinit var db: SQLiteDatabase

    override fun <S : User?> save(u: S): S
    {
        val userId = db.insert(TABLE_NAME, null, getContentValues(u))

        if (userId == -1L)
            throw SQLiteException("exception in save")

        u?.userId = userId

        return u
    }

    override fun findAll(): MutableIterable<User>
    {
        val projection = arrayOf(ID, NAME, USERNAME, PASSWORD, REGISTER_DATE)
        val cursor = db.query(TABLE_NAME, projection, null, null, null, null, null)

        cursor.use {
            val users = ArrayList<User>()

            if (it == null || !it.moveToFirst())
                return users
            do
                users.add(getUser(it))
            while (it.moveToNext())

            return users
        }
    }

    override fun findUsersSortByRegisterDate(): MutableIterable<User>
    {
        val projection = arrayOf(ID, NAME, USERNAME, PASSWORD, REGISTER_DATE)
        val cursor = db.query(TABLE_NAME, projection, null, null, null, null, REGISTER_DATE + " desc")

        cursor.use {
            val users = ArrayList<User>()

            if (it == null || !it.moveToFirst())
                return users
            do
                users.add(getUser(it))
            while (it.moveToNext())

            return users
        }
    }

    override fun delete(user: User) = deleteById(user.userId)

    override fun deleteById(id: Long)
    {
        db.delete(TABLE_NAME, "$ID=$id", null)
    }

    override fun deleteAll()
    {
        db.delete(TABLE_NAME, null, null)
    }

    override fun findUserByUsername(username: String): Optional<User>
    {
        val projection = arrayOf(ID, NAME, USERNAME, PASSWORD, REGISTER_DATE)
        val cursor = db.query(TABLE_NAME, projection, "$USERNAME = '$username'", null, null, null, null)

        cursor.use {
            val users = ArrayList<User>()

            if (it == null || !it.moveToFirst())
                return Optional.empty()

            return Optional.of(getUser(cursor))
        }
    }

    override fun findUsersByUsernameContains(text: String): MutableIterable<User>
    {
        val projection = arrayOf(ID, NAME, USERNAME, PASSWORD, REGISTER_DATE)
        val cursor = db.query(TABLE_NAME, projection, "$USERNAME like '%$text%'", null, null, null, null)

        cursor.use {
            val users = ArrayList<User>()

            if (it == null || !it.moveToFirst())
                return users
            do
                users.add(getUser(it))
            while (it.moveToNext())

            return users
        }
    }

    override fun existsByUsername(username: String) = findUserByUsername(username).isPresent

    override fun deleteAllUsers() = db.delete(TABLE_NAME, null, null)

    ////////////////////////////////////////////////////////////////////////////////////////////////
    override fun <S : User?> save(entities: MutableIterable<S>?): MutableIterable<S> {
        TODO("Not yet implemented")
    }

    override fun count(): Long
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<User>?)
    {
        TODO("Not yet implemented")
    }

    override fun exitsById(id: Long?): Boolean
    {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Long>?): MutableIterable<User> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long?): Optional<User>
    {
        TODO("Not yet implemented")
    }
}