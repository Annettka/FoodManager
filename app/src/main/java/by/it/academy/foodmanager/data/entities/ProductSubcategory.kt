package by.it.academy.foodmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_subcategory")
data class ProductSubcategory(
    @PrimaryKey(autoGenerate = false)
    val subcategoryName: String,
    val categoryName: String
)
