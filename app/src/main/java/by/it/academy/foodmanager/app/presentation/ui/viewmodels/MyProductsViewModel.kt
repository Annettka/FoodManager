package by.it.academy.foodmanager.app.presentation.ui.viewmodels

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.*
import by.it.academy.foodmanager.app.presentation.NotificationReceiver
import by.it.academy.foodmanager.app.domain.interactor.productinteractor.ProductInteractor
import by.it.academy.foodmanager.app.domain.interactor.productinteractor.ProductOut
import by.it.academy.foodmanager.app.domain.models.ProductDomain

class MyProductsViewModel(private val interactor: ProductInteractor) : ViewModel(), ProductOut {

    private var _allProducts = MutableLiveData<List<ProductDomain>>()
    val allProducts: LiveData<List<ProductDomain>> = _allProducts

    private var _product = MutableLiveData<ProductDomain>()
    val product: LiveData<ProductDomain> = _product

    init {
        interactor.setInteractorOut(this)
        interactor.getAllProducts()
    }

    fun deleteProduct(context: Context, product: ProductDomain) {
        interactor.deleteProduct(product)
        cancelNotification(context, product)
        interactor.getAllProducts()
    }

    fun cancelNotification(context: Context, product: ProductDomain) {
        val id = 1

        val intent = Intent(context, NotificationReceiver::class.java)
        intent.putExtra("text", 0)
        intent.putExtra("id", id)

        val pendingIntent = PendingIntent.getBroadcast(
            context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.cancel(pendingIntent)
    }

    override fun productsLoaded(products: List<ProductDomain>) {
        _allProducts.value = products
    }

    override fun productLoaded(product: ProductDomain) {
        _product.value = product
    }
}