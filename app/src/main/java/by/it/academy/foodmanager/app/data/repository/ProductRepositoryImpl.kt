package by.it.academy.foodmanager.app.data.repository

import by.it.academy.foodmanager.app.data.db.ProductDao
import by.it.academy.foodmanager.app.data.db.entities.Product
import by.it.academy.foodmanager.app.data.mappers.ProductCategoryPresentationMapper
import by.it.academy.foodmanager.app.data.mappers.toProduct
import by.it.academy.foodmanager.app.data.mappers.toProductPresent
import by.it.academy.foodmanager.app.domain.repository.ProductRepository
import by.it.academy.foodmanager.app.domain.models.ProductCategoryDomain
import by.it.academy.foodmanager.app.domain.models.ProductDomain

class ProductRepositoryImpl(private val productDao: ProductDao) : ProductRepository {

    override suspend fun getAllProducts(): List<ProductDomain> =
        productDao.getProductsByExpirationDate().map {
            it.toProductPresent()
        }

    override suspend fun getAllProductCategoriesWithSubcategories(): List<ProductCategoryDomain>  =
        ProductCategoryPresentationMapper().map(productDao.getAllProductCategoriesWithSubcategories())

    override suspend fun getProduct(productName: String): ProductDomain =
        productDao.getProduct(productName).toProductPresent()


    override suspend fun insertProduct(product: ProductDomain) {
        productDao.insertProduct(product.toProduct())
    }

    override suspend fun updateProduct(product: ProductDomain) {
        productDao.updateProduct(product.toProduct())
    }

    override suspend fun deleteProduct(product: ProductDomain) {
        productDao.deleteProduct(product.toProduct())
    }


    /*override suspend fun getAllProductCategories(): List<ProductCategory> =
        productDao.getAllProductCategories()

    override fun getAlphabetizedProducts() {
        productDao.getAlphabetizedProducts()
    }

    override fun getProductCategoryWithSubcategories(categoryName: String): ProductCategoryWithProductSubcategories {
        return productDao.getProductCategoryWithSubcategories(categoryName)
    }


    override fun getProductSubcategoryWithProducts(subcategoryName: String) {
        productDao.getProductCategoryWithSubcategories(subcategoryName)
    }*/
}