package com.jaehyeon.arlammanagertest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by Jaehyeon on 2022/08/13.
 */
class AlarmBroadCastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        Log.e(javaClass.simpleName, "onReceive: Event 처리.")
        NotificationService(context).apply {
            val message = intent?.extras?.get("data").toString()
            showNotification(message)
        }
    }

}