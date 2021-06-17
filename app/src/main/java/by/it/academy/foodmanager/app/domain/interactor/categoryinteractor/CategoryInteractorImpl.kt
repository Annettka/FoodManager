package by.it.academy.foodmanager.app.domain.interactor.categoryinteractor

import by.it.academy.foodmanager.app.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryInteractorImpl(private val repository: ProductRepository) : CategoryInteractor {
    private lateinit var out: CategoryOut

    override fun setInteractorOut(out: CategoryOut) {
        this.out = out
    }

    override fun getAllProductCategoriesWithSubcategories() {
        GlobalScope.launch {
            val categories = withContext(Dispatchers.IO) {
                repository.getAllProductCategoriesWithSubcategories()
            }
            withContext(Dispatchers.Main) {
                out.categoriesLoaded(categories)
            }
        }
    }
}