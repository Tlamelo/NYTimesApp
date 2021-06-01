package com.example.nytimes.model
import com.example.nytimes.di.DaggerApiComponent
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.Call
//import io.reactivex.Single
import javax.inject.Inject


class ArticlesService {
    @Inject
    lateinit var  api: ArticlesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getArticles(): Call<Article> {
        return api.getArticles()
    }

}