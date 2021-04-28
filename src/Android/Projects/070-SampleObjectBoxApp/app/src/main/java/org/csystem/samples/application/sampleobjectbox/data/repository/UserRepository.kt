package org.csystem.samples.application.sampleobjectbox.data.repository

import org.csystem.samples.application.sampleobjectbox.data.entity.User
import org.csystem.samples.application.sampleobjectbox.data.entity.User_
import org.csystem.samples.application.sampleobjectbox.data.objectbox.ObjectBox
import java.util.*
import java.util.stream.Collectors
import java.util.stream.StreamSupport

object UserRepository : IUserRepository {
    override fun <S : User> save(u: S): S
    {
        ObjectBox.userBox.put(u)
        return u
    }

    override fun findAll(): MutableIterable<User> = ObjectBox.userBox.query().build().find()

    override fun findUsersSortByRegisterDate(): MutableIterable<User>
    {
        return ObjectBox.userBox
            .query()
            .orderDesc(User_.registerDate)
            .build()
            .find()
    }

    override fun delete(user: User) = deleteById(user.id)

    override fun deleteById(id: Long)
    {
        ObjectBox.userBox.remove(id)
    }

    override fun deleteAll()
    {
        ObjectBox.userBox.removeAll()
    }

    override fun findUserByUsername(username: String): Optional<User>
    {
        return Optional.ofNullable(ObjectBox.userBox.query().equal(User_.username, username).build().findFirst())
    }

    override fun findUsersByUsernameContains(text: String): MutableIterable<User>
    {
        return ObjectBox.userBox.query().contains(User_.username, text).build().find()
    }

    override fun existsByUsername(username: String) = findUserByUsername(username).isPresent

    override fun deleteAllUsers() = ObjectBox.userBox.panicModeRemoveAll().toInt()

    override fun count(): Long
    {
        return ObjectBox.userBox.count()
    }

    override fun exitsById(id: Long) = findById(id).isPresent

    override fun findById(id: Long): Optional<User>
    {
        return  Optional.ofNullable(ObjectBox.userBox.query().equal(User_.id, id).build().findFirst())
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    override fun <S : User?> save(entities: MutableIterable<S>): MutableIterable<S>
    {
        var entityList = StreamSupport.stream(entities.spliterator(), false).collect(Collectors.toList())
        ObjectBox.userBox.put(entityList as Collection<S>)
        return entities
    }

    override fun deleteAll(entities: MutableIterable<User>?)
    {
        ObjectBox.userBox.removeAll()
    }
    override fun findAllById(ids: MutableIterable<Long>?): MutableIterable<User>
    {
        TODO("Not yet implemented")
    }
}