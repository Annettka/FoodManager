package by.it.academy.foodmanager.presentation.ui.viewmodels

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.*
import by.it.academy.foodmanager.domain.ProductRepository
import by.it.academy.foodmanager.data.entities.Product
import by.it.academy.foodmanager.domain.NotificationReceiver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyProductsViewModel(private val repository: ProductRepository) : ViewModel() {

    val allProducts: LiveData<List<Product>> = repository.getAllProducts().asLiveData()

    fun deleteProduct(context: Context, product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteProduct(product)
        cancelNotification(context, product)
    }

    fun getAlphabetizedProducts() = viewModelScope.launch(Dispatchers.IO) {
        repository.getAlphabetizedProducts()
    }

    fun cancelNotification(context: Context, product: Product) {
        val id = product.productId

        val intent = Intent(context, NotificationReceiver::class.java)
        intent.putExtra("text", 0)
        intent.putExtra("id", id)

        val pendingIntent = PendingIntent.getBroadcast(
            context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.cancel(pendingIntent)
    }
}