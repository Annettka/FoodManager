package by.it.academy.foodmanager.app.presentation.ui.viewmodels

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.it.academy.foodmanager.app.presentation.NotificationReceiver
import by.it.academy.foodmanager.app.domain.SaveBitmap
import by.it.academy.foodmanager.app.domain.converters.DateStringConverter
import by.it.academy.foodmanager.app.domain.interactor.productinteractor.ProductInteractor
import by.it.academy.foodmanager.app.domain.interactor.productinteractor.ProductOut
import by.it.academy.foodmanager.app.domain.models.ProductDomain
import java.util.*

class AddEditProductViewModel(
    private val interactor: ProductInteractor
) : ViewModel(), ProductOut {

    private val converter = DateStringConverter()

    private var _allProducts = MutableLiveData<List<ProductDomain>>()
    val allProducts: LiveData<List<ProductDomain>> = _allProducts

    private val _expirationDate = MutableLiveData<String>()
    val expirationDate: LiveData<String> = _expirationDate

    private val _creationDate = MutableLiveData<String>()
    val creationDate: LiveData<String> = _creationDate

    private val _notificationTime = MutableLiveData<Long>()
    val notificationTime: LiveData<Long> = _notificationTime

    private val _daysBeforeExpiration = MutableLiveData<Long>()
    val daysBeforeExpiration: LiveData<Long> = _daysBeforeExpiration

    private val _curProduct = MutableLiveData<ProductDomain>()
    val curProduct: LiveData<ProductDomain> = _curProduct

    init {
        val calendar = Calendar.getInstance()
        val date = converter.dateToString(calendar.time.time)
        _expirationDate.value = date
        _creationDate.value = date
        _notificationTime.value = calendar.timeInMillis
        interactor.setInteractorOut(this)
        interactor.getAllProducts()
    }

    fun setExpirationDate(date: Date){
        _expirationDate.value = converter.dateToString(date.time)
    }

    fun setDaysBeforeExpiration(days: Long) {
        _daysBeforeExpiration.value = days * 86400000
    }

    fun setNotificationTime(time: Long) {
        _notificationTime.value = time
    }

    fun insertProduct(product: ProductDomain) {
        interactor.insertProduct(product)
    }

    fun updateProduct(product: ProductDomain) {
        interactor.updateProduct(product)
        interactor.getAllProducts()
    }

    private fun getProduct(name: String) {
           interactor.getProduct(name)
    }

    fun saveBitmap(bitmap: Bitmap) {
        SaveBitmap().saveTempBitmap(bitmap)
    }

    fun isProductExist(product: ProductDomain): Boolean {
        if (allProducts.value == null){
            return false
        } else {
            getProduct(product.name)
            return curProduct.value == product
        }
    }

    fun createNotification(context: Context, text: String) {
        val exp: Long = converter.stringToDate(expirationDate.value ?: "0")
        val per: Long = daysBeforeExpiration.value ?: 0
        val time: Long = notificationTime.value ?: 0
        val notify = (time + exp) - per

        getProduct(text)
        val id = 1

        val intent = Intent(context, NotificationReceiver::class.java)
        intent.putExtra("text", text)
        intent.putExtra("id", id)

        val pendingIntent = PendingIntent.getBroadcast(
            context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.set(AlarmManager.RTC_WAKEUP, notify, pendingIntent)
    }

    override fun productsLoaded(products: List<ProductDomain>) {
    }

    override fun productLoaded(product: ProductDomain) {
        _curProduct.value = product
    }
}