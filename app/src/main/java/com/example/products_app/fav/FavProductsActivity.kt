package com.example.products_app.fav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.di_starterapplication.data.repository.ProductsRepositoryImpl
import com.example.products_app.AppContainer
import com.example.products_app.ProductsApplication
import com.example.products_app.all.AllProductFactory
import com.example.products_app.all.AllProductsViewModel
import com.example.products_app.data.local.ProductsDataBase
import com.example.products_app.data.local.ProductsLocalDataSource
import com.example.products_app.data.remote.ProductsRemoteDataSourceImpl
import com.example.products_app.data.remote.RetrofitHelper

class FavProductsActivity : ComponentActivity() {
    lateinit var appContainer : AppContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        appContainer = (application as ProductsApplication).appContainer
        setContent {
            val viewModel = ViewModelProvider(this,
                FavProductFactory(appContainer.productsRepository)
            )[FavProductsViewModel::class.java]
            FavProductsScreen(viewModel)
        }
    }
}

