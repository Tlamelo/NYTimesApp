package com.example.nytimes.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.nytimes.di.DaggerApiComponent
import com.example.nytimes.model.Article
import com.example.nytimes.model.ArticlesService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Response

class ListViewModel: ViewModel() {
    @Inject
    lateinit var articlesService:ArticlesService
    init {
        DaggerApiComponent.create().inject(this)
    }
    private val disposable = CompositeDisposable()
    val articles = MutableLiveData<Article>()
    val articleLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchArticles()
    }

    private fun fetchArticles(){
        loading.value = true
        val call = articlesService.getArticles()
        call.enqueue(object : retrofit2.Callback<Article>{
                override fun onResponse(call: Call<Article>, response: Response<Article>) {
                    if(response.isSuccessful) {
                        loading.value = false
                        articles.postValue(response.body())
                    } else {
                        articleLoadError.value = true
                        loading.value = false
                        articles.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Article>, t: Throwable) {
                    articleLoadError.value = true
                    loading.value = false
                    articles.postValue(null)
                }
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}