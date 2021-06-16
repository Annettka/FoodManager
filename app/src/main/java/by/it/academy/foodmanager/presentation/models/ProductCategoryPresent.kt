package by.it.academy.foodmanager.presentation.models

import java.io.Serializable

data class ProductCategoryPresent(
    val categoryName: String,
    val categoryIcon: Int,
    val subcategories: List<ProductSubcategoryPresent>
)

data class ProductSubcategoryPresent(
    val subcategoryName: String,
//    val products: List<ProductPresent>
)


data class ProductPresent(
    val productId: Int,
    val name: String,
    val comment: String,
    val creationDate: String,
    val expirationDate: String,
    val daysToNotify: Long,
    val timeToNotify: Long,
    val subcategoryName: String
) : Serializable