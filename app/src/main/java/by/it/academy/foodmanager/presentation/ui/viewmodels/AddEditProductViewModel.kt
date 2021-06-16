package by.it.academy.foodmanager.presentation.ui.viewmodels

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.it.academy.foodmanager.data.entities.Product
import by.it.academy.foodmanager.data.mappers.ProductDataMapper
import by.it.academy.foodmanager.domain.NotificationReceiver
import by.it.academy.foodmanager.domain.ProductRepository
import by.it.academy.foodmanager.domain.SaveBitmap
import by.it.academy.foodmanager.presentation.converters.DateStringConverter
import by.it.academy.foodmanager.presentation.models.ProductPresent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class AddEditProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val converter = DateStringConverter()

    private val _expirationDate = MutableLiveData<String>()
    val expirationDate: LiveData<String> = _expirationDate

    private val _creationDate = MutableLiveData<String>()
    val creationDate: LiveData<String> = _creationDate

    private val _notificationTime = MutableLiveData<Long>()
    val notificationTime: LiveData<Long> = _notificationTime

    private val _daysBeforeExpiration = MutableLiveData<Long>()
    val daysBeforeExpiration: LiveData<Long> = _daysBeforeExpiration

    private val _curProduct = MutableLiveData<Product>()
    val curProduct: LiveData<Product> = _curProduct

    fun setExpirationDate(date: Date) = viewModelScope.launch {
        _expirationDate.value = converter.dateToString(date.time)
    }

    fun setDaysBeforeExpiration(days: Long) = viewModelScope.launch {
        _daysBeforeExpiration.value = days * 86400000
    }

    fun setNotificationTime(time: Long) = viewModelScope.launch {
        _notificationTime.value = time
    }

    fun insertProduct(product: ProductPresent) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertProduct(ProductDataMapper().map(product))
    }

    fun updateProduct(product: ProductPresent) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateProduct(ProductDataMapper().map(product))
    }

    private fun getProduct(name: String) = viewModelScope.launch(Dispatchers.Main) {
        repository.getProduct(name).collect {
            _curProduct.value = it
        }
    }

    fun saveBitmap(bitmap: Bitmap) {
        SaveBitmap().saveTempBitmap(bitmap)
    }

    fun isProductExist(product: ProductPresent): Boolean {
        getProduct(product.name)
        return curProduct.value == ProductDataMapper().map(product)
    }

    fun createNotification(context: Context, text: String) {
        val exp: Long = converter.stringToDate(expirationDate.value ?: "0")
        val per: Long = daysBeforeExpiration.value ?: 0
        val time: Long = notificationTime.value ?: 0
        val notify = (time + exp) - per

        getProduct(text)
        val id = curProduct.value?.productId ?: 0

        val intent = Intent(context, NotificationReceiver::class.java)
        intent.putExtra("text", text)
        intent.putExtra("id", id)

        val pendingIntent = PendingIntent.getBroadcast(
            context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.set(AlarmManager.RTC_WAKEUP, notify, pendingIntent)
    }

    init {
        val calendar = Calendar.getInstance()
        val date = converter.dateToString(calendar.time.time)
        _expirationDate.value = date
        _creationDate.value = date
        _notificationTime.value = calendar.timeInMillis
    }
}