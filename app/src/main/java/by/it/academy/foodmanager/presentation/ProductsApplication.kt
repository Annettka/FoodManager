package by.it.academy.foodmanager.presentation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import by.it.academy.foodmanager.data.constants.CHANNEL_ID
import by.it.academy.foodmanager.data.constants.CHANNEL_NAME
import by.it.academy.foodmanager.presentation.di.databaseModule
import by.it.academy.foodmanager.presentation.di.repositoryModule
import by.it.academy.foodmanager.presentation.di.viewModelModule
import by.it.academy.foodmanager.presentation.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import kotlin.coroutines.coroutineContext

class ProductsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ProductsApplication)
            modules(listOf(databaseModule, repositoryModule, viewModelModule))
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