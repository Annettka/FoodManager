package by.it.academy.foodmanager.app.domain.interactor.productinteractor

import by.it.academy.foodmanager.app.domain.models.ProductDomain

interface ProductOut {
    fun productsLoaded(products: List<ProductDomain>)
    fun productLoaded(product: ProductDomain)
}