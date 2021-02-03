package org.csystem.application.todoinfo.repository

import org.csystem.application.todoinfo.entity.UserInfo
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class UserInfoRepository @Inject constructor() : IUserInfoRepository {
    companion object {
        private val mcUsers = ArrayList<UserInfo>()
        private var mcIndex = 0L
    }

    override fun count(): Long = mcUsers.size.toLong()

    override fun findAll(): MutableIterable<UserInfo> = mcUsers

    override fun findByUsername(username: String): Optional<UserInfo>
            = Optional.ofNullable(mcUsers.firstOrNull{ it.username == username })

    override fun <S : UserInfo?> save(entity: S): S
    {
        mcUsers.add(entity!!)
        entity.id = ++mcIndex

        return entity
    }




    //////////////////////





    override fun delete(entity: UserInfo?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll()
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<UserInfo>?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long?)
    {
        TODO("Not yet implemented")
    }

    override fun exitsById(id: Long?): Boolean
    {
        TODO("Not yet implemented")
    }



    override fun findAllById(ids: MutableIterable<Long>?): MutableIterable<UserInfo> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long?): Optional<UserInfo> {
        TODO("Not yet implemented")
    }

    override fun <S : UserInfo?> save(entities: MutableIterable<S>?): MutableIterable<S>
    {
        TODO("Not yet implemented")
    }
}