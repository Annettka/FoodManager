package by.it.academy.foodmanager.app.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_category")
data class ProductCategory(
    @PrimaryKey(autoGenerate = false)
    val categoryName: String,
    val categoryIcon: Int
)