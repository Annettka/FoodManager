package by.it.academy.foodmanager.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import by.it.academy.foodmanager.app.domain.converters.Converters
import by.it.academy.foodmanager.app.data.db.entities.Product
import by.it.academy.foodmanager.app.data.db.entities.ProductCategoriesWithSubcategoriesInit
import by.it.academy.foodmanager.app.data.db.entities.ProductCategory
import by.it.academy.foodmanager.app.data.db.entities.ProductSubcategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [ProductCategory::class, ProductSubcategory::class, Product::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
                )
                    .addCallback(ProductDatabaseCallback(scope, context))
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }

    class ProductDatabaseCallback(
        private val scope: CoroutineScope,
        private val context: Context
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.productDao(), context)
                }
            }
        }

        private suspend fun populateDatabase(productDao: ProductDao, context: Context) {
            val initializer = ProductCategoriesWithSubcategoriesInit(context)
            productDao.insertProductCategories(initializer.populateProductCategories())
            productDao.insertProductSubcategories(initializer.populateSubcatReadyMeals())
            productDao.insertProductSubcategories(initializer.populateSubcatSemiFinishedProducts())
            productDao.insertProductSubcategories(initializer.populateSubcatFruitsAndVegetables())
            productDao.insertProductSubcategories(initializer.populateSubcatBakeryAndConfectionery())
            productDao.insertProductSubcategories(initializer.populateSubcatMilkProductsCheeseEggs())
            productDao.insertProductSubcategories(initializer.populateSubcatMeat())
            productDao.insertProductSubcategories(initializer.populateSubcatFowl())
            productDao.insertProductSubcategories(initializer.populateSubcatFishCaviarSeafood())
            productDao.insertProductSubcategories(initializer.populateSubcatMeatGastronomy())
            productDao.insertProductSubcategories(initializer.populateSubcatJuicesDrinksWater())
            productDao.insertProductSubcategories(initializer.populateSubcatChocolateCandyCookiesAndOtherSweets())
            productDao.insertProductSubcategories(initializer.populateSubcatGrocery())
            productDao.insertProductSubcategories(initializer.populateSubcatFrozenFood())
            productDao.insertProductSubcategories(initializer.populateSubcatBabyFood())
            productDao.insertProductSubcategories(initializer.populateSubcatHealthyAndSportsNutrition())
            productDao.insertProductSubcategories(initializer.populateSubcatPetSupplies())
        }
    }

}