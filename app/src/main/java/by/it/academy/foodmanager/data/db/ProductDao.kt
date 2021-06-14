package by.it.academy.foodmanager.data.db

import androidx.room.*
import by.it.academy.foodmanager.data.entities.Product
import by.it.academy.foodmanager.data.entities.ProductCategory
import by.it.academy.foodmanager.data.entities.ProductSubcategory
import by.it.academy.foodmanager.data.entities.relations.ProductCategoryWithProductSubcategories
import by.it.academy.foodmanager.data.entities.relations.ProductSubcategoryWithProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCategories(productCategories: List<ProductCategory>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductSubcategories(productSubcategories: List<ProductSubcategory>)

    @Transaction
    @Query("SELECT * FROM product_category WHERE categoryName = :categoryName")
    fun getProductCategoryWithSubcategories(categoryName: String):
            Flow<ProductCategoryWithProductSubcategories>

    @Transaction
    @Query("SELECT * FROM product_category")
    fun getAllProductCategoriesWithSubcategories():
            Flow<List<ProductCategoryWithProductSubcategories>>

    @Transaction
    @Query("SELECT * FROM product_subcategory WHERE subcategoryName = :subcategoryName")
    fun getProductSubcategoryWithProducts(subcategoryName: String):
            Flow<List<ProductSubcategoryWithProducts>>

    @Query("SELECT * FROM product ORDER BY expirationDate ASC")
    fun getProductsByExpirationDate(): Flow<List<Product>>

    @Query("SELECT * FROM product ORDER BY name ASC")
    fun getAlphabetizedProducts(): Flow<List<Product>>

    @Query("SELECT * FROM product_category ORDER BY categoryName ASC")
    fun getAllProductCategories(): Flow<List<ProductCategory>>

    @Query("SELECT * FROM product WHERE name = :productName")
    fun getProduct(productName: String):
            Flow<Product>

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

}