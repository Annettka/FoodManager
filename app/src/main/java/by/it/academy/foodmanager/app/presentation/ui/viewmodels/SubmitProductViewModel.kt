package by.it.academy.foodmanager.app.presentation.ui.viewmodels

import androidx.lifecycle.*
import by.it.academy.foodmanager.app.domain.interactor.categoryinteractor.CategoryInteractor
import by.it.academy.foodmanager.app.domain.interactor.categoryinteractor.CategoryOut
import by.it.academy.foodmanager.app.domain.models.ProductCategoryDomain

class SubmitProductViewModel(private val interactor: CategoryInteractor) : ViewModel(),
    CategoryOut {

    private val _productCategories = MutableLiveData<List<ProductCategoryDomain>>()
    val productCategories: LiveData<List<ProductCategoryDomain>> = _productCategories

    init {
        interactor.setInteractorOut(this)
        interactor.getAllProductCategoriesWithSubcategories()
    }

    override fun categoriesLoaded(categories: List<ProductCategoryDomain>) {
       _productCategories.value = categories
    }
}