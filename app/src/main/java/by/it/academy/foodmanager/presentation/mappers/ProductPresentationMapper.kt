package by.it.academy.foodmanager.presentation.mappers

import by.it.academy.foodmanager.data.entities.Product
import by.it.academy.foodmanager.presentation.converters.DateStringConverter
import by.it.academy.foodmanager.presentation.models.ProductPresent

class ProductPresentationMapper {

    fun map(product: Product): ProductPresent {
        return ProductPresent(
            productId = product.productId,
            name = product.name,
            comment = product.comment,
            creationDate = DateStringConverter().dateToString(product.creationDate),
            expirationDate = DateStringConverter().dateToString(product.expirationDate),
            daysToNotify = product.daysToNotify,
            timeToNotify = product.timeToNotify,
            subcategoryName = product.subcategoryName
        )
    }
}