package com.example.products_app

import com.example.di_starterapplication.data.repository.ProductsRepository
import com.example.di_starterapplication.data.repository.ProductsRepositoryImpl
import com.example.products_app.all.AllProductsViewModel
import com.example.products_app.data.local.LocalDataSource
import com.example.products_app.data.local.ProductsDao
import com.example.products_app.data.local.ProductsDataBase
import com.example.products_app.data.local.ProductsLocalDataSource
import com.example.products_app.data.remote.ProductService
import com.example.products_app.data.remote.ProductsRemoteDataSourceImpl
import com.example.products_app.data.remote.RemoteDataSource
import com.example.products_app.fav.FavProductsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val myModule = module {
    single<Retrofit>{
        val baseUrl = "https://dummyjson.com/"
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<ProductService> {
        get<Retrofit>().create(ProductService::class.java)
    }
    single<ProductsDataBase>{
        ProductsDataBase.getInstance(androidContext())
    }
    single<ProductsDao> {
        get<ProductsDataBase>().getProductsDao()
    }
    single<RemoteDataSource> {
        ProductsRemoteDataSourceImpl(get())
    }
    single<LocalDataSource>{
        ProductsLocalDataSource(get())
    }
    single<ProductsRepository> {
        ProductsRepositoryImpl(get(),get())
    }
    viewModel<AllProductsViewModel> {
        AllProductsViewModel(get())
    }
    viewModel <FavProductsViewModel> {
        FavProductsViewModel(get())
    }

}