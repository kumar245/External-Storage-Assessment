package com.example.myapplication.di

import com.example.myapplication.data.FilesRepository
import com.example.myapplication.utils.rx.SchedulerProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, RxModule::class])
interface MainComponent {
  fun filesRepository(): FilesRepository
  fun schedulerProvider(): SchedulerProvider
}