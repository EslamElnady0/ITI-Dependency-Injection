package com.example.products_app

import android.content.Context
import com.example.di_starterapplication.data.repository.ProductsRepository
import com.example.di_starterapplication.data.repository.ProductsRepositoryImpl
import com.example.products_app.all.AllProductFactory
import com.example.products_app.data.local.LocalDataSource
import com.example.products_app.data.local.ProductsDao
import com.example.products_app.data.local.ProductsDataBase
import com.example.products_app.data.local.ProductsLocalDataSource
import com.example.products_app.data.remote.ProductService
import com.example.products_app.data.remote.ProductsRemoteDataSourceImpl
import com.example.products_app.data.remote.RemoteDataSource
import com.example.products_app.data.remote.RetrofitHelper
import com.example.products_app.fav.FavProductFactory

interface AppContainer{
    val productsDao : ProductsDao
    val productService: ProductService
    val remoteDataSource: RemoteDataSource
    val localDataSource: LocalDataSource
    val productsRepository: ProductsRepository
}


class AppContainerImpl(val context: Context) : AppContainer{
    override val productsDao: ProductsDao by lazy {
        ProductsDataBase.getInstance(context).getProductsDao()
    }
    override val productService: ProductService by lazy {
        RetrofitHelper.service
    }
    override val remoteDataSource: RemoteDataSource by lazy {
        ProductsRemoteDataSourceImpl(productService)
    }
    override val localDataSource: LocalDataSource by lazy {
        ProductsLocalDataSource(productsDao)
    }
    override val productsRepository: ProductsRepository by lazy {
        ProductsRepositoryImpl.getInstance(remoteDataSource, localDataSource)
    }
}