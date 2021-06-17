package by.it.academy.foodmanager.app.data.db.entities

import android.content.Context
import by.it.academy.foodmanager.R

class ProductCategoriesWithSubcategoriesInit(val context: Context) {

    val categories: Array<String> = context.resources.getStringArray(R.array.product_categories)

    private val categoriesIcons: List<Int> = listOf(
        R.drawable.ic_cook,
        R.drawable.ic_precooked,
        R.drawable.ic_fruits_vegetables,
        R.drawable.ic_bread,
        R.drawable.ic_dairy_products,
        R.drawable.ic_chop,
        R.drawable.ic_chicken,
        R.drawable.ic_seafood,
        R.drawable.ic_salami,
        R.drawable.ic_soft_drink,
        R.drawable.ic_sweets,
        R.drawable.ic_groceries,
        R.drawable.ic_frozen_goods,
        R.drawable.ic_baby_food,
        R.drawable.ic_healthy,
        R.drawable.ic_pet_food
    )

    fun populateProductCategories(): List<ProductCategory> {
        val list: MutableList<ProductCategory> = mutableListOf()
        for (index in categories.indices) {
            list.add(ProductCategory(categories[index], categoriesIcons[index]))
        }
        return list
    }

    fun populateSubcatReadyMeals(): List<ProductSubcategory> {
        val readyMealsSubcategories: Array<String> =
            context.resources.getStringArray(R.array.ready_meals_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in readyMealsSubcategories.indices) {
            list.add(ProductSubcategory(readyMealsSubcategories[index], categories[0]))
        }
        return list
    }

    fun populateSubcatSemiFinishedProducts(): List<ProductSubcategory> {
        val semiFinishedProductsSubcategories: Array<String> =
            context.resources.getStringArray(R.array.semi_finished_products_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in semiFinishedProductsSubcategories.indices) {
            list.add(ProductSubcategory(semiFinishedProductsSubcategories[index], categories[1]))
        }
        return list
    }

    fun populateSubcatFruitsAndVegetables(): List<ProductSubcategory> {
        val fruitsAndVegetablesSubcategories: Array<String> =
            context.resources.getStringArray(R.array.fruits_and_vegetables_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in fruitsAndVegetablesSubcategories.indices) {
            list.add(ProductSubcategory(fruitsAndVegetablesSubcategories[index], categories[2]))
        }
        return list
    }

    fun populateSubcatBakeryAndConfectionery(): List<ProductSubcategory> {
        val bakeryAndConfectionerySubcategories: Array<String> =
            context.resources.getStringArray(R.array.bakery_and_confectionery_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in bakeryAndConfectionerySubcategories.indices) {
            list.add(ProductSubcategory(bakeryAndConfectionerySubcategories[index], categories[3]))
        }
        return list
    }

    fun populateSubcatMilkProductsCheeseEggs(): List<ProductSubcategory> {
        val milkProductsCheeseEggsSubcategories: Array<String> =
            context.resources.getStringArray(R.array.milk_products_cheese_eggs_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in milkProductsCheeseEggsSubcategories.indices) {
            list.add(ProductSubcategory(milkProductsCheeseEggsSubcategories[index], categories[4]))
        }
        return list
    }

    fun populateSubcatMeat(): List<ProductSubcategory> {
        val meatSubcategories: Array<String> =
            context.resources.getStringArray(R.array.meat_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in meatSubcategories.indices) {
            list.add(ProductSubcategory(meatSubcategories[index], categories[5]))
        }
        return list
    }

    fun populateSubcatFowl(): List<ProductSubcategory> {
        val fowlSubcategories: Array<String> =
            context.resources.getStringArray(R.array.fowl_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in fowlSubcategories.indices) {
            list.add(ProductSubcategory(fowlSubcategories[index], categories[6]))
        }
        return list
    }

    fun populateSubcatFishCaviarSeafood(): List<ProductSubcategory> {
        val fishCaviarSeafoodSubcategories: Array<String> =
            context.resources.getStringArray(R.array.fish_caviar_seafood_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in fishCaviarSeafoodSubcategories.indices) {
            list.add(ProductSubcategory(fishCaviarSeafoodSubcategories[index], categories[7]))
        }
        return list
    }

    fun populateSubcatMeatGastronomy(): List<ProductSubcategory> {
        val meatGastronomySubcategories: Array<String> =
            context.resources.getStringArray(R.array.meat_gastronomy_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in meatGastronomySubcategories.indices) {
            list.add(ProductSubcategory(meatGastronomySubcategories[index], categories[8]))
        }
        return list
    }

    fun populateSubcatJuicesDrinksWater(): List<ProductSubcategory> {
        val juicesDrinksWaterSubcategories: Array<String> =
            context.resources.getStringArray(R.array.juices_drinks_water_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in juicesDrinksWaterSubcategories.indices) {
            list.add(ProductSubcategory(juicesDrinksWaterSubcategories[index], categories[9]))
        }
        return list
    }

    fun populateSubcatChocolateCandyCookiesAndOtherSweets(): List<ProductSubcategory> {
        val chocolateCandyCookiesAndOtherSweetsSubcategories: Array<String> =
            context.resources.getStringArray(R.array.chocolate_candy_cookies_and_other_sweets_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in chocolateCandyCookiesAndOtherSweetsSubcategories.indices) {
            list.add(ProductSubcategory(chocolateCandyCookiesAndOtherSweetsSubcategories[index], categories[10]))
        }
        return list
    }

    fun populateSubcatGrocery(): List<ProductSubcategory> {
        val grocerySubcategories: Array<String> =
            context.resources.getStringArray(R.array.grocery_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in grocerySubcategories.indices) {
            list.add(ProductSubcategory(grocerySubcategories[index], categories[11]))
        }
        return list
    }

    fun populateSubcatFrozenFood(): List<ProductSubcategory> {
        val frozenFoodSubcategories: Array<String> =
            context.resources.getStringArray(R.array.frozen_food_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in frozenFoodSubcategories.indices) {
            list.add(ProductSubcategory(frozenFoodSubcategories[index], categories[12]))
        }
        return list
    }

    fun populateSubcatBabyFood(): List<ProductSubcategory> {
        val babyFoodSubcategories: Array<String> =
            context.resources.getStringArray(R.array.baby_food_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in babyFoodSubcategories.indices) {
            list.add(ProductSubcategory(babyFoodSubcategories[index], categories[13]))
        }
        return list
    }

    fun populateSubcatHealthyAndSportsNutrition(): List<ProductSubcategory> {
        val healthyAndSportsNutritionSubcategories: Array<String> =
            context.resources.getStringArray(R.array.healthy_and_sports_nutrition_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in healthyAndSportsNutritionSubcategories.indices) {
            list.add(ProductSubcategory(healthyAndSportsNutritionSubcategories[index], categories[14]))
        }
        return list
    }

    fun populateSubcatPetSupplies(): List<ProductSubcategory> {
        val petSuppliesSubcategories: Array<String> =
            context.resources.getStringArray(R.array.pet_supplies_subcategories)
        val list: MutableList<ProductSubcategory> = mutableListOf()
        for (index in petSuppliesSubcategories.indices) {
            list.add(ProductSubcategory(petSuppliesSubcategories[index], categories[15]))
        }
        return list
    }
}