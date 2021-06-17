package by.it.academy.foodmanager.app.data.db.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import by.it.academy.foodmanager.app.data.db.entities.ProductCategory
import by.it.academy.foodmanager.app.data.db.entities.ProductSubcategory

data class ProductCategoryWithProductSubcategories(
    @Embedded
    val productCategory: ProductCategory,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "categoryName"
    )
    val productSubcategories: List<ProductSubcategory>
)