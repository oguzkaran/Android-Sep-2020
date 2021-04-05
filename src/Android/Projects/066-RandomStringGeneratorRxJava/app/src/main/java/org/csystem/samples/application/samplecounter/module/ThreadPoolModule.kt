package org.csystem.samples.application.samplecounter.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors

@Module
@InstallIn(SingletonComponent::class)
object ThreadPoolModule {
    @Provides
    fun provideThreadPool() = Executors.newFixedThreadPool(2)
}