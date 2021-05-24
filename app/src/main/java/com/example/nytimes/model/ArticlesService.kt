package com.example.nytimes.model

import com.example.nytimes.model.network.Resource
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ArticlesService {

    //private val BASE_URL = "https://api.nytimes.com/svc/"
    private val BASE_URL = "https://raw.githubusercontent.com"

    private  val api: ArticlesApi

    init {
        val builder = OkHttpClient.Builder()
        val client = builder.build()
        api = Retrofit.Builder() //creates framework for retrofit
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ArticlesApi::class.java)
    }

    fun getArticles(): Single<List<Article>> {
        return api.getArticles()
    }

}