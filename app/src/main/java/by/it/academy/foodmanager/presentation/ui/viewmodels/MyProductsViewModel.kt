package by.it.academy.foodmanager.presentation.ui.viewmodels

import androidx.lifecycle.*
import by.it.academy.foodmanager.domain.ProductRepository
import by.it.academy.foodmanager.data.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyProductsViewModel(private val repository: ProductRepository) : ViewModel() {

    val allProducts: LiveData<List<Product>> = repository.getAllProducts().asLiveData()

    fun deleteProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteProduct(product)
    }

    fun getAlphabetizedProducts() = viewModelScope.launch(Dispatchers.IO) {
        repository.getAlphabetizedProducts()
    }
}