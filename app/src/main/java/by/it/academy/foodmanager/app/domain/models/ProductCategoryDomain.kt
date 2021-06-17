package by.it.academy.foodmanager.app.domain.models

import java.io.Serializable

data class ProductCategoryDomain(
    val categoryName: String,
    val categoryIcon: Int,
    val subcategories: List<ProductSubcategoryDomain>
)

data class ProductSubcategoryDomain(
    val subcategoryName: String
)


data class ProductDomain(
    val name: String,
    val comment: String,
    val creationDate: String,
    val expirationDate: String,
    val daysToNotify: Long,
    val timeToNotify: Long,
    val subcategoryName: String
) : Serializable