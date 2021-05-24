package com.example.nytimes.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title")
    val title: String?,
    @SerializedName("byline")
    val author: String?,
    @SerializedName("published_date")
    val publishedDate  : String?,
    @SerializedName("abstract")
    var description : String?
    )

