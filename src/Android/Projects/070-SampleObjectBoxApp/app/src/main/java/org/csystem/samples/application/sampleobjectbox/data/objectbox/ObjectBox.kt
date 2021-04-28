package org.csystem.samples.application.sampleobjectbox.data.objectbox

import android.content.Context
import io.objectbox.Box
import io.objectbox.BoxStore
import org.csystem.samples.application.sampleobjectbox.data.entity.MyObjectBox
import org.csystem.samples.application.sampleobjectbox.data.entity.User

object ObjectBox {
    lateinit var boxStore: BoxStore
        private set

    lateinit var userBox: Box<User>
        private set

    fun initialize(context: Context)
    {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()

        userBox = boxStore.boxFor(User::class.java)
    }
}