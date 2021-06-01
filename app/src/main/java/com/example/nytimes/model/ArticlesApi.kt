package com.example.nytimes.model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query



interface ArticlesApi {
    @GET("mostpopular/v2/viewed/7.json")
    fun getArticles(@Query("api-key")  apiKey : String = "ZvzBr4L56Tus2qVji2YSnFxMnwlnyk4R"): Call<Article>

}