package by.it.academy.foodmanager.app.data.mappers

import by.it.academy.foodmanager.app.data.db.entities.ProductSubcategory
import by.it.academy.foodmanager.app.data.db.entities.relations.ProductCategoryWithProductSubcategories
import by.it.academy.foodmanager.app.domain.models.ProductCategoryDomain
import by.it.academy.foodmanager.app.domain.models.ProductSubcategoryDomain

class ProductCategoryPresentationMapper {

    fun map(productCategoryWithSubcategories: List<ProductCategoryWithProductSubcategories>): List<ProductCategoryDomain> {
        val result: MutableList<ProductCategoryDomain> = mutableListOf()
        for (index in productCategoryWithSubcategories.indices) {
            result.add(
                ProductCategoryDomain(
                    categoryName = productCategoryWithSubcategories[index].productCategory.categoryName,
                    categoryIcon = productCategoryWithSubcategories[index].productCategory.categoryIcon,
                    subcategories = mapList(productCategoryWithSubcategories[index].productSubcategories)
                )
            )
        }
        return result
    }

    private fun mapList(productSubcategory: List<ProductSubcategory>): List<ProductSubcategoryDomain> {
        val res: MutableList<ProductSubcategoryDomain> = mutableListOf()

        for (index in productSubcategory.indices) {
            res.add(
                ProductSubcategoryDomain(
                    subcategoryName = productSubcategory[index].subcategoryName
                )
            )
        }
        return res
    }
}