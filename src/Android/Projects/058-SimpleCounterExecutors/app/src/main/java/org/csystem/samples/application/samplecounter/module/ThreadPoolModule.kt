package org.csystem.samples.application.samplecounter.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.samples.application.samplecounter.provider.ExecutorServiceProvider
import java.util.concurrent.ExecutorService
import javax.inject.Provider

@Module
@InstallIn(ActivityComponent::class)
abstract class ThreadPoolModule {
    @Binds
    //@Singleton
    abstract fun provideThreadPool(executorServiceProvider: ExecutorServiceProvider) : Provider<ExecutorService>
}