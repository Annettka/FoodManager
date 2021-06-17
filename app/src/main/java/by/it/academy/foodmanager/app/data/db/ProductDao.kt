package by.it.academy.foodmanager.app.data.db

import androidx.room.*
import by.it.academy.foodmanager.app.data.db.entities.Product
import by.it.academy.foodmanager.app.data.db.entities.ProductCategory
import by.it.academy.foodmanager.app.data.db.entities.ProductSubcategory
import by.it.academy.foodmanager.app.data.db.entities.relations.ProductCategoryWithProductSubcategories
import by.it.academy.foodmanager.app.data.db.entities.relations.ProductSubcategoryWithProducts

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCategories(productCategories: List<ProductCategory>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductSubcategories(productSubcategories: List<ProductSubcategory>)

    @Transaction
    @Query("SELECT * FROM product_category")
    suspend fun getAllProductCategoriesWithSubcategories():
            List<ProductCategoryWithProductSubcategories>

    @Query("SELECT * FROM product ORDER BY expirationDate ASC")
    suspend fun getProductsByExpirationDate(): List<Product>

    @Query("SELECT * FROM product WHERE name = :productName")
    suspend fun getProduct(productName: String): Product

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)


    /* @Transaction
     @Query("SELECT * FROM product_category WHERE categoryName = :categoryName")
     fun getProductCategoryWithSubcategories(categoryName: String):
             ProductCategoryWithProductSubcategories


    @Transaction
    @Query("SELECT * FROM product_subcategory WHERE subcategoryName = :subcategoryName")
    fun getProductSubcategoryWithProducts(subcategoryName: String):
            List<ProductSubcategoryWithProducts>*/

    /*

    @Query("SELECT * FROM product ORDER BY name ASC")
    fun getAlphabetizedProducts(): List<Product>*/

    /* @Query("SELECT * FROM product_category ORDER BY categoryName ASC")
     fun getAllProductCategories(): List<ProductCategory>*/

}