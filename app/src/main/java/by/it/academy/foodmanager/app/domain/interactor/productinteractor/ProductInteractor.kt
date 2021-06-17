package by.it.academy.foodmanager.app.domain.interactor.productinteractor

import by.it.academy.foodmanager.app.domain.models.ProductDomain

interface ProductInteractor {
    fun setInteractorOut(out: ProductOut)
    fun getAllProducts()
    fun insertProduct(product: ProductDomain)
    fun updateProduct(product: ProductDomain)
    fun deleteProduct(product: ProductDomain)
    fun getProduct(productName: String)

}