package by.it.academy.foodmanager

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import by.it.academy.foodmanager.app.presentation.constants.CHANNEL_ID
import by.it.academy.foodmanager.app.presentation.constants.CHANNEL_NAME
import by.it.academy.foodmanager.di.databaseModule
import by.it.academy.foodmanager.di.domainModule
import by.it.academy.foodmanager.di.repositoryModule
import by.it.academy.foodmanager.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ProductsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ProductsApplication)
            modules(listOf(databaseModule, repositoryModule, domainModule, viewModelModule))
        }

        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(channel)
        }
    }
}