package com.example.products_app.fav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

class FavProductsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = koinViewModel<FavProductsViewModel>()
            FavProductsScreen(viewModel)
        }
    }
}

