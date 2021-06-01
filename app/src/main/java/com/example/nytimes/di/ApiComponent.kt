package com.example.nytimes.di

import com.example.nytimes.model.ArticlesService
import com.example.nytimes.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: ArticlesService)
    fun inject(viewModel: ListViewModel)
}