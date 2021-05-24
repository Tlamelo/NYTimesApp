package com.example.nytimes.model


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query



interface ArticlesApi {
    //@GET("mostpopular/v2/viewed/7.json")
    //fun getArticles(@Query("api-key")  apiKey : String = ApiModule.API_KEY): Single<List<Article>>

    @GET("Tlamelo/nytimes/main/datafinal.json")
    fun getArticles(): Single<List<Article>>

}