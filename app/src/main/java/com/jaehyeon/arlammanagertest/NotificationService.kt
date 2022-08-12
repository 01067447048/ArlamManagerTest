package com.jaehyeon.arlammanagertest

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

/**
 * Created by Jaehyeon on 2022/08/13.
 */
class NotificationService(
    private val context: Context
) {

    private val manager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(message: String) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, "alarm_id")
            .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
            .setContentTitle("Message")
            .setContentText("The message is $message")
            .setContentIntent(activityPendingIntent)
            .build()

        manager.notify(1, notification)
    }

}