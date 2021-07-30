package org.csystem.application.backgroundservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import org.csystem.application.backgroundservice.global.Global
import java.io.DataOutputStream
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class RandomNumberGeneratorService : Service() {
    override fun onBind(intent: Intent): IBinder = throw NotImplementedError("Not implemented")

    override fun onCreate()
    {
        Toast.makeText(this, "RandomNumberGeneratorService.onCreate", Toast.LENGTH_LONG).show()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        Toast.makeText(this, "RandomNumberGeneratorService.onStartCommand", Toast.LENGTH_LONG).show()

        val selfIntent = intent!!
        val filename = selfIntent.getStringExtra("filename")
        val min = selfIntent.getIntExtra("min", 0)
        val max = selfIntent.getIntExtra("max", 100)
        var count = selfIntent.getIntExtra("count", 0)
        val fos = openFileOutput(filename, MODE_APPEND)
        val dos = DataOutputStream(fos);

        fun mapCallback() : Int
        {
            --count
            return Random.nextInt(min, max + 1).apply {
                dos.writeInt(this)
            }
        }

        Observable.interval(1, TimeUnit.SECONDS).takeWhile{count != 0}
            .map{ mapCallback() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()}, {}, {stopSelf(); fos.close()})

        return START_REDELIVER_INTENT //Eğer servis yok edilirse son intent değeri ile tekrar servis başlatılır (redeliver last intent)
    }

    override fun onDestroy()
    {
        Toast.makeText(this, "RandomNumberGeneratorService.onDestroy", Toast.LENGTH_LONG).show()
        synchronized(applicationContext) {Global.isServiceRunning = false}
        super.onDestroy()
    }
}