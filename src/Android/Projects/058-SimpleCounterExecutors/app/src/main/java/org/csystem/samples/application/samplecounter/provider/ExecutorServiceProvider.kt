package org.csystem.samples.application.samplecounter.provider

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Provider

class ExecutorServiceProvider @Inject constructor() : Provider<ExecutorService> {
    override fun get() = Executors.newCachedThreadPool()
}