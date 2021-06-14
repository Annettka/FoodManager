package by.it.academy.foodmanager.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import by.it.academy.foodmanager.R
import by.it.academy.foodmanager.data.converters.Converters
import by.it.academy.foodmanager.data.entities.Product
import by.it.academy.foodmanager.data.entities.ProductCategory
import by.it.academy.foodmanager.data.entities.ProductSubcategory
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
                    .addCallback(ProductDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }

    private class ProductDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.productDao())
                }
            }
        }

        suspend fun populateDatabase(productDao: ProductDao) {

            val productCategories: List<ProductCategory> = listOf(
                ProductCategory("Готовые блюда", R.drawable.ic_cook),
                ProductCategory("Полуфабрикаты", 0),
                ProductCategory("Овощи и фрукты", R.drawable.ic_vegetable),
                ProductCategory("Хлебобулочные и кондитерские изделия", R.drawable.ic_bread),
                ProductCategory("Молочные продукты, сыр, яйца", R.drawable.ic_milk),
                ProductCategory("Мясо", R.drawable.ic_meat),
                ProductCategory("Птица", R.drawable.ic_bird),
                ProductCategory("Рыба, икра, морепродукты", R.drawable.ic_fish),
                ProductCategory("Мясная гастрономия", 0),
                ProductCategory("Соки, напитки, вода", 0),
                ProductCategory(
                    "Шоколад, конфеты, печенье и другие сладости",
                    R.drawable.ic_chocolate
                ),
                ProductCategory("Бакалея", 0),
                ProductCategory("Замороженные продукты", 0),
                ProductCategory("Детское питание", 0),
                ProductCategory("Здоровое и спортивное питание", 0),
                ProductCategory("Зоотовары", 0)
            )

            productDao.insertProductCategories(productCategories)

            val readyMealsSubcategories: List<ProductSubcategory> = listOf<ProductSubcategory>(
                ProductSubcategory("Салаты и холодные закуски", productCategories[0].categoryName),
                ProductSubcategory("Котлеты, голубцы, отбивные", productCategories[0].categoryName),
                ProductSubcategory("Суши", productCategories[0].categoryName),
                ProductSubcategory("Выпечка и блины", productCategories[0].categoryName),
                ProductSubcategory("Гарниры", productCategories[0].categoryName),
                ProductSubcategory("Копчености и гриль", productCategories[0].categoryName),
                ProductSubcategory("Соусы", productCategories[0].categoryName),
                ProductSubcategory("Рулеты и руляды", productCategories[0].categoryName)
            )
            productDao.insertProductSubcategories(readyMealsSubcategories)

            val semiFinishedProductsSubcategories: List<ProductSubcategory> =
                listOf<ProductSubcategory>(
                    ProductSubcategory("Шашлыки", productCategories[1].categoryName),
                    ProductSubcategory(
                        "Маринованные изделия из мяса птицы",
                        productCategories[1].categoryName
                    ),
                    ProductSubcategory("Колбасы", productCategories[1].categoryName),
                    ProductSubcategory("Котлеты и голубцы", productCategories[1].categoryName),
                    ProductSubcategory("Фарши", productCategories[1].categoryName),
                    ProductSubcategory("Маринованные овощи", productCategories[1].categoryName),
                    ProductSubcategory("Мучные полуфабрикаты", productCategories[1].categoryName)
                )
            productDao.insertProductSubcategories(semiFinishedProductsSubcategories)

            val fruitsAndVegetablesSubcategories: List<ProductSubcategory> = listOf(
                ProductSubcategory("Овощи", productCategories[2].categoryName),
                ProductSubcategory("Фрукты", productCategories[2].categoryName),
                ProductSubcategory("Грибы", productCategories[2].categoryName),
                ProductSubcategory("Ягоды", productCategories[2].categoryName),
                ProductSubcategory("Зелень и салаты", productCategories[2].categoryName)
            )
            productDao.insertProductSubcategories(fruitsAndVegetablesSubcategories)

            val bakeryAndConfectionerySubcategories: List<ProductSubcategory> =
                listOf<ProductSubcategory>(
                    ProductSubcategory("Хлеб и сдоба", productCategories[3].categoryName),
                    ProductSubcategory(
                        "Печенье, вафли, пряники",
                        productCategories[3].categoryName
                    ),
                    ProductSubcategory("Торты, пирожные", productCategories[3].categoryName),
                    ProductSubcategory(
                        "Выпечка, мучные сладости",
                        productCategories[3].categoryName
                    ),
                    ProductSubcategory("Сухари, сушки", productCategories[3].categoryName),
                    ProductSubcategory(
                        "Другие кондитерские изделия",
                        productCategories[3].categoryName
                    )
                )
            productDao.insertProductSubcategories(bakeryAndConfectionerySubcategories)

            val milkProductsCheeseEggsSubcategories: List<ProductSubcategory> =
                listOf<ProductSubcategory>(
                    ProductSubcategory("Молоко", productCategories[4].categoryName),
                    ProductSubcategory(
                        "Кефир, кисломолочные изделия",
                        productCategories[4].categoryName
                    ),
                    ProductSubcategory("Масло, маргарин, жиры", productCategories[4].categoryName),
                    ProductSubcategory("Творожные продукты", productCategories[4].categoryName),
                    ProductSubcategory("Сметана и сливки", productCategories[4].categoryName),
                    ProductSubcategory("Йогурт и десерты", productCategories[4].categoryName),
                    ProductSubcategory("Мороженое", productCategories[4].categoryName),
                    ProductSubcategory("Сыр", productCategories[4].categoryName),
                    ProductSubcategory("Яйца", productCategories[4].categoryName)
                )
            productDao.insertProductSubcategories(milkProductsCheeseEggsSubcategories)

            val meatSubcategories: List<ProductSubcategory> = listOf<ProductSubcategory>(
                ProductSubcategory("Свинина", productCategories[5].categoryName),
                ProductSubcategory("Говядина", productCategories[5].categoryName),
                ProductSubcategory("Баранина", productCategories[5].categoryName),
                ProductSubcategory("Кролик", productCategories[5].categoryName),
                ProductSubcategory("Субпродукты", productCategories[5].categoryName)
            )
            productDao.insertProductSubcategories(meatSubcategories)

            val fowlSubcategories: List<ProductSubcategory> = listOf<ProductSubcategory>(
                ProductSubcategory("Индейка", productCategories[6].categoryName),
                ProductSubcategory("Куры, цыплята", productCategories[6].categoryName),
                ProductSubcategory("Утка", productCategories[6].categoryName)
            )
            productDao.insertProductSubcategories(fowlSubcategories)

            val fishCaviarSeafoodSubcategories: List<ProductSubcategory> =
                listOf<ProductSubcategory>(
                    ProductSubcategory("Икра", productCategories[7].categoryName),
                    ProductSubcategory("Капуста морская", productCategories[7].categoryName),
                    ProductSubcategory("Рыбные закуски", productCategories[7].categoryName),
                    ProductSubcategory("Рыбные пресервы", productCategories[7].categoryName),
                    ProductSubcategory("Рыбные консервы", productCategories[7].categoryName),
                    ProductSubcategory("Крабовые палочки", productCategories[7].categoryName),
                    ProductSubcategory("Рыба свежемороженая", productCategories[7].categoryName),
                    ProductSubcategory("Рыба соленая", productCategories[7].categoryName),
                    ProductSubcategory("Рыба копченая", productCategories[7].categoryName),
                    ProductSubcategory(
                        "Рыбные снеки, рыба вяленая, сушеная",
                        productCategories[7].categoryName
                    ),
                    ProductSubcategory("Морепродукты", productCategories[7].categoryName),
                    ProductSubcategory("Рыбные полуфабрикаты", productCategories[7].categoryName)
                )
            productDao.insertProductSubcategories(fishCaviarSeafoodSubcategories)

            val meatGastronomySubcategories: List<ProductSubcategory> = listOf<ProductSubcategory>(
                ProductSubcategory("Колбасные изделия", productCategories[8].categoryName),
                ProductSubcategory("Мясные изделия", productCategories[8].categoryName),
                ProductSubcategory(
                    "Паштеты, прочие изделия из мясопродуктов",
                    productCategories[8].categoryName
                )
            )
            productDao.insertProductSubcategories(meatGastronomySubcategories)

            val juicesDrinksWaterSubcategories: List<ProductSubcategory> =
                listOf<ProductSubcategory>(
                    ProductSubcategory("Вода", productCategories[9].categoryName),
                    ProductSubcategory("Квас", productCategories[9].categoryName),
                    ProductSubcategory("Соки, нектары, морсы", productCategories[9].categoryName),
                    ProductSubcategory("Напитки", productCategories[9].categoryName),
                    ProductSubcategory("Энергетические напитки", productCategories[9].categoryName),
                    ProductSubcategory("Кофе", productCategories[9].categoryName),
                    ProductSubcategory("Чай", productCategories[9].categoryName),
                    ProductSubcategory("Другие напитки", productCategories[9].categoryName)
                )
            productDao.insertProductSubcategories(juicesDrinksWaterSubcategories)

            val chocolateCandyCookiesAndOtherSweetsSubcategories: List<ProductSubcategory> =
                listOf<ProductSubcategory>(
                    ProductSubcategory("Шоколад", productCategories[10].categoryName),
                    ProductSubcategory("Конфеты", productCategories[10].categoryName),
                    ProductSubcategory("Жевательная резинка", productCategories[10].categoryName),
                    ProductSubcategory("Восточные сладости", productCategories[10].categoryName),
                    ProductSubcategory(
                        "Шоколадные и арахисовые пасты",
                        productCategories[10].categoryName
                    ),
                    ProductSubcategory("Другие сладости", productCategories[10].categoryName)
                )
            productDao.insertProductSubcategories(
                chocolateCandyCookiesAndOtherSweetsSubcategories
            )

            val grocerySubcategories: List<ProductSubcategory> = listOf<ProductSubcategory>(
                ProductSubcategory("Мука, смеси для выпечки", productCategories[11].categoryName),
                ProductSubcategory("Макаронные изделия", productCategories[11].categoryName),
                ProductSubcategory("Крупы и бобовые", productCategories[11].categoryName),
                ProductSubcategory(
                    "Хлопья, каши, мюсли, сухие завтраки",
                    productCategories[11].categoryName
                ),
                ProductSubcategory(
                    "Сахар, соль, приправы, кондитерские добавки",
                    productCategories[11].categoryName
                ),
                ProductSubcategory("Масло растительное, уксус", productCategories[11].categoryName),
                ProductSubcategory(
                    "Майонезы, соусы, кетчуп, горчица, хрен",
                    productCategories[11].categoryName
                ),
                ProductSubcategory(
                    "Продукты быстрого приготовления",
                    productCategories[11].categoryName
                ),
                ProductSubcategory(
                    "Чипсы, сухарики, попкорн, сушеная рыба",
                    productCategories[11].categoryName
                ),
                ProductSubcategory(
                    "Орехи, сухофрукты, семечки",
                    productCategories[11].categoryName
                ),
                ProductSubcategory("Консервы", productCategories[11].categoryName),
                ProductSubcategory("Товары восточной кухни", productCategories[11].categoryName)
            )
            productDao.insertProductSubcategories(grocerySubcategories)

            val frozenFoodSubcategories: List<ProductSubcategory> = listOf<ProductSubcategory>(
                ProductSubcategory("Овощи замороженные", productCategories[12].categoryName),
                ProductSubcategory("Грибы замороженные", productCategories[12].categoryName),
                ProductSubcategory("Фрукты замороженные", productCategories[12].categoryName),
                ProductSubcategory("Ягоды замороженные", productCategories[12].categoryName),
                ProductSubcategory("Вторые блюда", productCategories[12].categoryName),
                ProductSubcategory("Полуфабрикаты", productCategories[12].categoryName),
                ProductSubcategory("Лёд пищевой", productCategories[12].categoryName),
                ProductSubcategory("Пиццы, тесто", productCategories[12].categoryName),
                ProductSubcategory("Мука, смеси для выпечки", productCategories[12].categoryName)
            )
            productDao.insertProductSubcategories(frozenFoodSubcategories)

            val babyFoodSubcategories: List<ProductSubcategory> = listOf<ProductSubcategory>(
                ProductSubcategory("Смеси, каши", productCategories[13].categoryName),
                ProductSubcategory("Консервы, пюре", productCategories[13].categoryName),
                ProductSubcategory("Вода, соки, напитки", productCategories[13].categoryName),
                ProductSubcategory("Печенье", productCategories[13].categoryName),
                ProductSubcategory("Другое", productCategories[13].categoryName)
            )
            productDao.insertProductSubcategories(babyFoodSubcategories)

            val healthyAndSportsNutritionSubcategories: List<ProductSubcategory> =
                listOf<ProductSubcategory>(
                    ProductSubcategory("Кондитерские изделия", productCategories[14].categoryName),
                    ProductSubcategory("Бакалея", productCategories[14].categoryName),
                    ProductSubcategory("Наборы для похудения", productCategories[14].categoryName),
                    ProductSubcategory("Напитки диетические", productCategories[14].categoryName),
                    ProductSubcategory("Продукты без глютена", productCategories[14].categoryName),
                    ProductSubcategory("Соевые продукты", productCategories[14].categoryName),
                    ProductSubcategory(
                        "Низкокалорийные продукты",
                        productCategories[14].categoryName
                    ),
                    ProductSubcategory(
                        "Низкобелковые продукты",
                        productCategories[14].categoryName
                    ),
                    ProductSubcategory("Спортивное питание", productCategories[14].categoryName),
                    ProductSubcategory(
                        "Протеиносодержащие продукты",
                        productCategories[14].categoryName
                    ),

                    )
            productDao.insertProductSubcategories(healthyAndSportsNutritionSubcategories)

            val petSuppliesSubcategories: List<ProductSubcategory> = listOf<ProductSubcategory>(
                ProductSubcategory("Корма для кошек", productCategories[15].categoryName),
                ProductSubcategory("Корма для собак", productCategories[15].categoryName),
                ProductSubcategory(
                    "Корма для птиц, рыбок, грызунов",
                    productCategories[15].categoryName
                ),
                ProductSubcategory("Лакомства для кошек", productCategories[15].categoryName),
                ProductSubcategory("Лакомства для собак", productCategories[15].categoryName)
            )
            productDao.insertProductSubcategories(petSuppliesSubcategories)
        }
    }

}