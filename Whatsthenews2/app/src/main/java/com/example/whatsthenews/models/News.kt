package com.example.whatsthenews.models

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class News (

    @SerializedName("source") val source : Source,
    @SerializedName("author") val author : String,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("url") val url : String,
    @SerializedName("urlToImage") val urlToImage : String,
    @SerializedName("publishedAt") val publishedAt : String,
    @SerializedName("content") val content : String
):Serializable

data class Source (

    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String
):Serializable