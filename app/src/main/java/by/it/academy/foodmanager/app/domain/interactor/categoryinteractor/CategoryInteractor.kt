package by.it.academy.foodmanager.app.domain.interactor.categoryinteractor

interface CategoryInteractor {
    fun setInteractorOut(out: CategoryOut)
    fun getAllProductCategoriesWithSubcategories()
}