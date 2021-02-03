package org.csystem.application.todoinfo.repository.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.application.todoinfo.data.converter.ITodoInfoDTOConverter
import org.csystem.application.todoinfo.data.converter.TodoInfoDTOConverter

@Module
@InstallIn(ActivityComponent::class)
abstract class TodoInfoDTOConverterModule {
    @Binds
    abstract fun bindTodoInfoDTOConverter(todoInfoDTOConverter: TodoInfoDTOConverter) : ITodoInfoDTOConverter
}