package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.DaggerMainComponent
import com.example.myapplication.di.DataModule
import com.example.myapplication.di.MainComponent
import com.example.myapplication.di.RxModule

class FilesApplication : Application() {

  lateinit var mainComponent: MainComponent
    private set

  override fun onCreate() {
    super.onCreate()

    mainComponent = DaggerMainComponent.builder()
        .dataModule(DataModule(this))
        .rxModule(RxModule())
        .build()
  }
}
