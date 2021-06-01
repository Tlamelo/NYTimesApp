package com.example.nytimes.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList


data class Article (
    @SerializedName("status") val status : String,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("num_results") val num_results : Int,
    @SerializedName("results") val results : ArrayList<Results>
)
data class Results (
    @SerializedName("title")val title: String ,
    @SerializedName("byline")val author: String,
    @SerializedName("published_date")val publishedDate: String,
    @SerializedName("abstract")var description: String
)
