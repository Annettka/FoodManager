package by.it.academy.foodmanager.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import by.it.academy.foodmanager.data.entities.ProductCategory
import by.it.academy.foodmanager.data.entities.ProductSubcategory

data class ProductCategoryWithProductSubcategories(
    @Embedded
    val productCategory: ProductCategory,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "categoryName"
    )
    val productSubcategories: List<ProductSubcategory>
)