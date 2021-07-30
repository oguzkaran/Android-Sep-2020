package org.csystem.application.backgroundservice

import android.app.IntentService
import android.content.Intent
import android.os.Looper
import android.os.Message
import android.widget.Toast
import java.io.DataOutputStream
import java.lang.ref.WeakReference
import kotlin.random.Random

//Dikkat IntentService sınıfın Android API Level 30'dan itibaren deprecated oldu. Bunun yerine JobScheduler kullanılmalı. Bir sonraki örneğe bakınız
class RandomNumberGeneratorService : IntentService("RandomNumberGeneratorService") {
    private lateinit var mHandler: GeneratorHandler

    private class GeneratorHandler(service: RandomNumberGeneratorService)
        : android.os.Handler(Looper.myLooper()!!) {
        private val mServiceWeakReference: WeakReference<RandomNumberGeneratorService>

        init {
            mServiceWeakReference = WeakReference(service)
        }

        override fun handleMessage(msg: Message)
        {
            val service = mServiceWeakReference.get()

            Toast.makeText(service, msg.what.toString(), Toast.LENGTH_SHORT).show()

            super.handleMessage(msg)
        }
    }

    override fun onCreate()
    {
        Toast.makeText(this, "RandomNumberGeneratorService.onCreate", Toast.LENGTH_LONG).show()
        mHandler = GeneratorHandler(this)
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?)
    {
        val selfIntent = intent!!
        val filename = selfIntent.getStringExtra("filename")
        val min = selfIntent.getIntExtra("min", 0)
        val max = selfIntent.getIntExtra("max", 100)
        val count = selfIntent.getIntExtra("count", 0)
        val fos = openFileOutput(filename, MODE_APPEND)
        val dos = DataOutputStream(fos);

        for (i in 1..count)
            Random.nextInt(min, max + 1).apply {
                mHandler.sendEmptyMessage(this)
                dos.writeInt(this)
                Thread.sleep(1000)
            }
    }

    override fun onDestroy()
    {
        Toast.makeText(this, "RandomNumberGeneratorService.onDestroy", Toast.LENGTH_LONG).show()
        super.onDestroy()
    }
}