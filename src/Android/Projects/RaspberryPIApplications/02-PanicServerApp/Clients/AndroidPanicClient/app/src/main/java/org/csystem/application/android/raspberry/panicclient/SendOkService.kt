package org.csystem.application.android.raspberry.panicclient

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.reactivex.rxjava3.core.Observable
import org.csystem.application.android.raspberry.panicclient.config.HOST
import org.csystem.application.android.raspberry.panicclient.config.OK_UDP_PORT
import org.csystem.application.android.raspberry.panicclient.global.g_sendOK
import org.csystem.util.net.UdpUtil
import java.util.concurrent.TimeUnit

class SendOkService : Service() {
    override fun onBind(intent: Intent): IBinder
    {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        Observable.interval(500, TimeUnit.MILLISECONDS)
            .takeWhile{ g_sendOK }
            .subscribe({UdpUtil.sendInt(HOST, OK_UDP_PORT, 1)}, {}, {stopSelf()})

        return START_REDELIVER_INTENT
    }
}