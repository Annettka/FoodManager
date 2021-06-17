package by.it.academy.foodmanager.app.domain.interactor.productinteractor

import by.it.academy.foodmanager.app.domain.models.ProductDomain
import by.it.academy.foodmanager.app.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductInteractorImpl(private val repository: ProductRepository) : ProductInteractor {

    private lateinit var out: ProductOut

    override fun setInteractorOut(out: ProductOut) {
        this.out = out
    }

    override fun getAllProducts() {
        GlobalScope.launch {
            val products = withContext(Dispatchers.IO) {
                repository.getAllProducts()
            }
            withContext(Dispatchers.Main) {
                out.productsLoaded(products)
            }
        }
    }

    override fun insertProduct(product: ProductDomain) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertProduct(product)
            }
        }
    }

    override fun updateProduct(product: ProductDomain) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateProduct(product)
            }
        }
    }

    override fun deleteProduct(product: ProductDomain) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteProduct(product)
            }
        }
    }

    override fun getProduct(productName: String) {
        GlobalScope.launch {
            val product = withContext(Dispatchers.IO) {
                repository.getProduct(productName)
            }
            withContext(Dispatchers.Main) {
                out.productLoaded(product)
            }
        }
    }


}