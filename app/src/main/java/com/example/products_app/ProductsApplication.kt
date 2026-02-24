package com.example.products_app

import android.app.Application

class ProductsApplication : Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainerImpl(this)
    }
}