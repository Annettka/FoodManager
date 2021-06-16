package by.it.academy.foodmanager.domain

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import by.it.academy.foodmanager.R
import by.it.academy.foodmanager.data.constants.CHANNEL_ID
import by.it.academy.foodmanager.presentation.ui.MainActivity
import by.it.academy.foodmanager.presentation.ui.fragments.MyProductsFragment

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val mainIntent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(intent.getStringExtra("text"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(intent.getIntExtra("id", 0), notification)

    }

}