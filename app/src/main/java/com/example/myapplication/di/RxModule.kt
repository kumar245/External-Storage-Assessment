package com.example.myapplication.di

import com.example.myapplication.utils.rx.ApplicationProvider
import com.example.myapplication.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RxModule {

  @Singleton
  @Provides
  fun provideSchedulerProvider(): SchedulerProvider = ApplicationProvider()
}