package com.example.products_app.di

import android.content.Context
import com.example.products_app.data.local.ProductsDao
import com.example.products_app.data.local.ProductsDataBase
import com.example.products_app.data.remote.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideRetrofit(): Retrofit{
        val baseUrl = "https://dummyjson.com/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService{
        return retrofit.create(ProductService::class.java)
    }
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ProductsDataBase{
        return ProductsDataBase.getInstance(context)
    }

    @Provides
    fun provideDao(dataBase: ProductsDataBase): ProductsDao {
        return dataBase.getProductsDao()
    }
}

