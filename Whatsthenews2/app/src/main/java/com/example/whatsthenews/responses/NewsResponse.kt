package com.example.whatsthenews.responses

import com.example.whatsthenews.models.News
import com.google.gson.annotations.SerializedName

data class NewsResponse(
        @SerializedName("totalResults")val totalResults:Int,
        @SerializedName("articles") val articles:List<News>)