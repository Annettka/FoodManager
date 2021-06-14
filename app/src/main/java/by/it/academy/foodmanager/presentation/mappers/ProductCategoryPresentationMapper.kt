package by.it.academy.foodmanager.presentation.mappers

import by.it.academy.foodmanager.data.entities.ProductSubcategory
import by.it.academy.foodmanager.data.entities.relations.ProductCategoryWithProductSubcategories
import by.it.academy.foodmanager.presentation.models.ProductCategoryPresent
import by.it.academy.foodmanager.presentation.models.ProductSubcategoryPresent

class ProductCategoryPresentationMapper {
    fun map(productCategoryWithSubcategories: List<ProductCategoryWithProductSubcategories>): List<ProductCategoryPresent> {
        val result: MutableList<ProductCategoryPresent> = mutableListOf()
        for (index in productCategoryWithSubcategories.indices) {
            result.add(
                ProductCategoryPresent(
                    categoryName = productCategoryWithSubcategories[index].productCategory.categoryName,
                    subcategories = mapList(productCategoryWithSubcategories[index].productSubcategories)
                )
            )
        }
        return result
    }

    private fun mapList(productSubcategory: List<ProductSubcategory>): List<ProductSubcategoryPresent> {
        val res: MutableList<ProductSubcategoryPresent> = mutableListOf()

        for (index in productSubcategory.indices) {
            res.add(
                ProductSubcategoryPresent(
                    subcategoryName = productSubcategory[index].subcategoryName
                )
            )
        }
        return res
    }
}