package by.it.academy.foodmanager.presentation.ui.viewmodels

import androidx.lifecycle.*
import by.it.academy.foodmanager.data.entities.Product
import by.it.academy.foodmanager.domain.ProductRepository
import by.it.academy.foodmanager.data.entities.ProductCategory
import by.it.academy.foodmanager.presentation.mappers.ProductCategoryPresentationMapper
import by.it.academy.foodmanager.presentation.models.ProductCategoryPresent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SubmitProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _productCategories = MutableLiveData<List<ProductCategoryPresent>>()
    val productCategories: LiveData<List<ProductCategoryPresent>> = _productCategories

    init {
        viewModelScope.launch {
            getAllProductCategoriesWithSubcategories()
        }
    }

    private suspend fun getAllProductCategoriesWithSubcategories() {
        repository.getAllProductCategoriesWithSubcategories().collect {
            _productCategories.value = ProductCategoryPresentationMapper().map(it)
        }
    }
}