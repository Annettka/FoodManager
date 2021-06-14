package by.it.academy.foodmanager.data.entities

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_category")
data class ProductCategory(
    @PrimaryKey(autoGenerate = false)
    val categoryName: String,
    val categoryIcon: Int
)