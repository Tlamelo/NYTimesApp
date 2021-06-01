package com.example.nytimes.di

import com.example.nytimes.model.ArticlesApi
import com.example.nytimes.model.ArticlesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://api.nytimes.com/svc/"


    @Provides
    fun provideArticlesApi(): ArticlesApi{
        return Retrofit.Builder() //creates framework for retrofit
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(ArticlesApi::class.java)
    }
    @Provides
    fun provideArticlesService(): ArticlesService{
        return ArticlesService()
    }
}