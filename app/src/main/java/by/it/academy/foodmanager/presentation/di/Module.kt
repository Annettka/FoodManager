package by.it.academy.foodmanager.presentation.di


import by.it.academy.foodmanager.data.db.ProductDatabase
import by.it.academy.foodmanager.data.repository.ProductRepositoryImpl
import by.it.academy.foodmanager.domain.ProductRepository
import by.it.academy.foodmanager.presentation.ui.viewmodels.AddEditProductViewModel
import by.it.academy.foodmanager.presentation.ui.viewmodels.MyProductsViewModel
import by.it.academy.foodmanager.presentation.ui.viewmodels.SubmitProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    val applicationScope = CoroutineScope(SupervisorJob())
    single { ProductDatabase.getDatabase(androidContext(), applicationScope) }
    single { get<ProductDatabase>().productDao() }
}

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}

val viewModelModule = module {

    viewModel { MyProductsViewModel(repository = get()) }
    viewModel { SubmitProductViewModel(repository = get()) }
    viewModel { AddEditProductViewModel(repository = get()) }
}