package org.csystem.application.todoinfo.repository.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.application.todoinfo.repository.ITodoInfoRepository
import org.csystem.application.todoinfo.repository.IUserInfoRepository
import org.csystem.application.todoinfo.repository.TodoInfoRepository
import org.csystem.application.todoinfo.repository.UserInfoRepository

@Module
@InstallIn(ActivityComponent::class)
abstract class TodoInfoApplicationRepositoryModule {
    @Binds
    abstract fun bindUserInfoRepository(userInfoRepository: UserInfoRepository) : IUserInfoRepository

    @Binds
    abstract fun bindTodoInfoRepository(todoInfoRepository: TodoInfoRepository) : ITodoInfoRepository
}