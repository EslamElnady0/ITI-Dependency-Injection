package com.example.products_app.all

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.lifecycle.ViewModelProvider
import com.example.di_starterapplication.data.repository.ProductsRepositoryImpl
import com.example.products_app.AppContainer
import com.example.products_app.ProductsApplication
import com.example.products_app.data.local.ProductsDataBase
import com.example.products_app.data.local.ProductsLocalDataSource
import com.example.products_app.data.remote.ProductsRemoteDataSourceImpl
import com.example.products_app.data.remote.RetrofitHelper

class AllProductsActivity : ComponentActivity() {
    lateinit var appContainer : AppContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = (application as ProductsApplication).appContainer
        setContent {

            val viewModel = ViewModelProvider(this,
                appContainer.allProductsFactory
            )[AllProductsViewModel::class.java]

            AllProductsScreen(viewModel)
        }
    }
}
