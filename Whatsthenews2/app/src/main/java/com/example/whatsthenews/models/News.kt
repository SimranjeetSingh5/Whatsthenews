package com.example.whatsthenews.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class News(@SerializedName("name") val source:List<String>,
                @SerializedName("title")val title:String,
                @SerializedName("content") val content:String,
                @SerializedName("publishedAt")val date:String,
                @SerializedName("url")val url:String,
                @SerializedName("urlToImage")val image:String):Serializable {

}