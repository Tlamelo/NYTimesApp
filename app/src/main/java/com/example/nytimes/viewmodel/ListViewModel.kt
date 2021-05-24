package com.example.nytimes.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.nytimes.model.Article
import com.example.nytimes.model.ArticlesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {

    private val articlesService = ArticlesService()
    private val disposable = CompositeDisposable()
    val articles = MutableLiveData<List<Article>>()
    val articleLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchArticles()
    }

    private fun fetchArticles(){
        loading.value = true
        disposable.add(
            articlesService.getArticles()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Article>>(){
                    override fun onSuccess(value: List<Article>?) {
                        articles.value = value
                        articleLoadError.value = false
                        loading.value = false

                    }

                    override fun onError(e: Throwable?) {
                        articleLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}