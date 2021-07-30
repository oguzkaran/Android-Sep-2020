package org.csystem.application.android.raspberry.panicclient.global

import android.content.Context
import android.content.Intent
import org.csystem.application.android.raspberry.panicclient.SendOkService

var g_sendOK = true

fun startOKService(context: Context)
{
    context.startService(Intent(context, SendOkService::class.java))
}
