package ai.arturxdroid.itunessearch.di

import ai.arturxdroid.itunessearch.network.ItunesApiService
import ai.arturxdroid.itunessearch.network.ItunesApiServiceImpl
import ai.arturxdroid.itunessearch.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModule {

    val appModule = module {

        single<ItunesApiService> { ItunesApiServiceImpl() }

        viewModel<MainViewModel> { MainViewModel(get()) }
    }

}