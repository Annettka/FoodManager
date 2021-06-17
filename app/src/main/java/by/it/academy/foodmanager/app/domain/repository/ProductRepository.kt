package by.it.academy.foodmanager.app.domain.repository

import by.it.academy.foodmanager.app.domain.models.ProductCategoryDomain
import by.it.academy.foodmanager.app.domain.models.ProductDomain

interface ProductRepository {

    suspend fun getAllProducts(): List<ProductDomain>
    suspend fun getAllProductCategoriesWithSubcategories(): List<ProductCategoryDomain>
    suspend fun insertProduct(product: ProductDomain)
    suspend fun updateProduct(product: ProductDomain)
    suspend fun deleteProduct(product: ProductDomain)
    suspend fun getProduct(productName: String): ProductDomain

/*
    fun getAllProductCategories(): List<ProductCategory>
    fun getAlphabetizedProducts()
    fun getProductCategoryWithSubcategories(categoryName: String): ProductCategoryWithProductSubcategories
    fun getProductSubcategoryWithProducts(subcategoryName: String)*/
}