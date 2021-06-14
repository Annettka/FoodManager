package by.it.academy.foodmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import by.it.academy.foodmanager.data.converters.Converters
import java.util.*

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val productId: Int,
    val name: String,
    val comment: String,
    val creationDate: Long,
    val expirationDate: Long,
    val daysToNotify: Long,
    val timeToNotify: Long,
    val subcategoryName: String
)
