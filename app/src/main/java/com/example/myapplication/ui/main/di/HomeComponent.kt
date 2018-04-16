package com.example.myapplication.ui.main.di

import com.example.myapplication.di.MainComponent
import com.example.myapplication.di.scopes.PerView
import com.example.myapplication.ui.main.HomeActivity
import dagger.Component

@PerView
@Component(dependencies = [MainComponent::class])
interface HomeComponent {
  fun inject(homeActivity: HomeActivity)
}