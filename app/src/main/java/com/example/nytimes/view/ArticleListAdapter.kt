package com.example.nytimes.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nytimes.R
import com.example.nytimes.model.Article
import kotlinx.android.synthetic.main.item_article.view.*


class ArticleListAdapter(var articles: ArrayList<Article>): RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {
    class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val articleName = view.name
        private val articleAuthor = view.author
        private  val publishedDate = view.date
        fun bind(article:Article){
            articleName.text = article.title
            articleAuthor.text = article.author
            publishedDate.text = article.publishedDate
        }
    }

    fun updateArticles(newArticles: List<Article>){
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()//Tells the system we have a new list of items
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ArticleViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_article,parent,false)
    )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() = articles.size
}