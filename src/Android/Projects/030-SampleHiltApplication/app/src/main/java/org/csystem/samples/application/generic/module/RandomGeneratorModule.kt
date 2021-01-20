package org.csystem.samples.application.generic.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.samples.application.generic.annotation.provider.AuthRandom
import org.csystem.samples.application.generic.annotation.provider.AuthRandomWithSeed
import kotlin.random.Random

@Module
@InstallIn(ActivityComponent::class)
object RandomGeneratorModule {
    @AuthRandomWithSeed
    @Provides
    fun createRandomWithSeed() = Random(100)

    @AuthRandom
    @Provides
    fun createRandom() = Random(200)
}