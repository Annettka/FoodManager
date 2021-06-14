package by.it.academy.foodmanager.domain

import by.it.academy.foodmanager.data.entities.Product
import by.it.academy.foodmanager.data.entities.ProductCategory
import by.it.academy.foodmanager.data.entities.relations.ProductCategoryWithProductSubcategories
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProducts(): Flow<List<Product>>
    fun getAllProductCategories(): Flow<List<ProductCategory>>
    fun getAlphabetizedProducts()
    fun getProductCategoryWithSubcategories(categoryName: String): Flow<ProductCategoryWithProductSubcategories>
    fun getAllProductCategoriesWithSubcategories(): Flow<List<ProductCategoryWithProductSubcategories>>
    fun getProductSubcategoryWithProducts(subcategoryName: String)
    suspend fun insertProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    fun getProduct(productName: String): Flow<Product>

}