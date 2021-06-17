package by.it.academy.foodmanager.app.data.mappers

import by.it.academy.foodmanager.app.data.db.entities.Product
import by.it.academy.foodmanager.app.domain.converters.DateStringConverter
import by.it.academy.foodmanager.app.domain.models.ProductDomain



fun Product.toProductPresent() = ProductDomain(
    name = name,
    comment = comment,
    creationDate = DateStringConverter().dateToString(creationDate),
    expirationDate = DateStringConverter().dateToString(expirationDate),
    daysToNotify = daysToNotify,
    timeToNotify = timeToNotify,
    subcategoryName = subcategoryName
)

fun ProductDomain.toProduct() = Product(
    productId = 0,
    name = name,
    comment = comment,
    creationDate = DateStringConverter().stringToDate(creationDate),
    expirationDate = DateStringConverter().stringToDate(expirationDate),
    daysToNotify = daysToNotify,
    timeToNotify = timeToNotify,
    subcategoryName = subcategoryName
)