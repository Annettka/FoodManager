package by.it.academy.foodmanager.di


import by.it.academy.foodmanager.app.data.db.ProductDatabase
import by.it.academy.foodmanager.app.data.repository.ProductRepositoryImpl
import by.it.academy.foodmanager.app.domain.interactor.categoryinteractor.CategoryInteractor
import by.it.academy.foodmanager.app.domain.interactor.categoryinteractor.CategoryInteractorImpl
import by.it.academy.foodmanager.app.domain.interactor.productinteractor.ProductInteractor
import by.it.academy.foodmanager.app.domain.interactor.productinteractor.ProductInteractorImpl
import by.it.academy.foodmanager.app.domain.repository.ProductRepository
import by.it.academy.foodmanager.app.presentation.ui.viewmodels.AddEditProductViewModel
import by.it.academy.foodmanager.app.presentation.ui.viewmodels.MyProductsViewModel
import by.it.academy.foodmanager.app.presentation.ui.viewmodels.SubmitProductViewModel
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

val domainModule = module {
    single <ProductInteractor>{ ProductInteractorImpl(get()) }
    single <CategoryInteractor>{ CategoryInteractorImpl(get()) }
}

val viewModelModule = module {

    viewModel { MyProductsViewModel(get()) }
    viewModel { SubmitProductViewModel(get()) }
    viewModel { AddEditProductViewModel(get()) }
}