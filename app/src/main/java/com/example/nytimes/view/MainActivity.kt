package com.example.nytimes.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.nytimes.R
import com.example.nytimes.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val articlesAdapter = ArticleListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        articlesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articlesAdapter
        }
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.articles.observe(this, Observer { articles ->
            articles?.let {
                articlesList.visibility = View.VISIBLE
                articlesAdapter.updateArticles(it)}
        })

        viewModel.articleLoadError.observe(this,Observer{ isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading->
            isLoading?.let{
                loading_view.visibility= if(it) View.VISIBLE else View.GONE
                if(it){
                    list_error.visibility = View.GONE
                    articlesList.visibility = View.GONE
                }
            }
        })
    }
}