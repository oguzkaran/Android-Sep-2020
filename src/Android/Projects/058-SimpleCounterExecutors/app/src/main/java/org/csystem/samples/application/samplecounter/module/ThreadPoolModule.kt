package org.csystem.samples.application.samplecounter.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.util.concurrent.Executors

@Module
@InstallIn(ActivityComponent::class)
object ThreadPoolModule {
    @Provides
    fun provideThreadPool() = Executors.newCachedThreadPool()
}