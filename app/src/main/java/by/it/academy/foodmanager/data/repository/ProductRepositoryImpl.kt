package by.it.academy.foodmanager.data.repository

import android.content.Context
import by.it.academy.foodmanager.data.db.ProductDao
import by.it.academy.foodmanager.data.entities.Product
import by.it.academy.foodmanager.data.entities.ProductCategory
import by.it.academy.foodmanager.data.entities.relations.ProductCategoryWithProductSubcategories
import by.it.academy.foodmanager.domain.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(private val productDao: ProductDao) : ProductRepository {

    override fun getAllProducts(): Flow<List<Product>> = productDao.getProductsByExpirationDate()

    override fun getAllProductCategories(): Flow<List<ProductCategory>> =
        productDao.getAllProductCategories()

    override suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    override suspend fun updateProduct(product: Product) {
        productDao.updateProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

    override fun getProduct(productName: String): Flow<Product> {
        return productDao.getProduct(productName)
    }

    override fun getAlphabetizedProducts() {
        productDao.getAlphabetizedProducts()
    }

    override fun getProductCategoryWithSubcategories(categoryName: String): Flow<ProductCategoryWithProductSubcategories> {
        return productDao.getProductCategoryWithSubcategories(categoryName)
    }

    override fun getAllProductCategoriesWithSubcategories(): Flow<List<ProductCategoryWithProductSubcategories>> {
        return productDao.getAllProductCategoriesWithSubcategories()
    }

    override fun getProductSubcategoryWithProducts(subcategoryName: String) {
        productDao.getProductCategoryWithSubcategories(subcategoryName)
    }
}