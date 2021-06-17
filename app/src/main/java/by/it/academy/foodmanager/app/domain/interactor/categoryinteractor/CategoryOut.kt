package by.it.academy.foodmanager.app.domain.interactor.categoryinteractor

import by.it.academy.foodmanager.app.domain.models.ProductCategoryDomain

interface CategoryOut {
    fun categoriesLoaded(categories: List<ProductCategoryDomain>)
}