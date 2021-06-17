package by.it.academy.foodmanager.app.data.db.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import by.it.academy.foodmanager.app.data.db.entities.Product
import by.it.academy.foodmanager.app.data.db.entities.ProductSubcategory

data class ProductSubcategoryWithProducts(
    @Embedded
    val productSubcategory: ProductSubcategory,
    @Relation(
        parentColumn = "subcategoryName",
        entityColumn = "subcategoryName"
    )
    val products: List<Product>
)