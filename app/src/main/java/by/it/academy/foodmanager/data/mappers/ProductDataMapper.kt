package by.it.academy.foodmanager.data.mappers

import by.it.academy.foodmanager.data.entities.Product
import by.it.academy.foodmanager.presentation.converters.DateStringConverter
import by.it.academy.foodmanager.presentation.models.ProductPresent

class ProductDataMapper {

    fun map(product: ProductPresent): Product {
        return Product(
            productId = product.productId,
            name = product.name,
            comment = product.comment,
            creationDate = DateStringConverter().stringToDate(product.creationDate),
            expirationDate = DateStringConverter().stringToDate(product.expirationDate),
            daysToNotify = product.daysToNotify,
            timeToNotify = product.timeToNotify,
            subcategoryName = product.subcategoryName
        )
    }
}