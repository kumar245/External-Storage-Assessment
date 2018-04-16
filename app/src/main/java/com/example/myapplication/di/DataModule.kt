package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.FilesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val context: Context) {

  @Singleton
  @Provides
  fun provideFilesRepository(): FilesRepository = FilesRepository(context)
}