package com.example.whatsthenews.network

import com.example.whatsthenews.models.News
import com.example.whatsthenews.responses.NewsResponse
import com.example.whatsthenews.util.Constant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?country=in")
    fun getTopHeadlines( @Query("apiKey") apiKey: String = Constant.API_KEY): Call<NewsResponse>?



    companion object {

        var retrofitService: ApiService? = null

        fun getInstance() : ApiService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                        .baseUrl("https://newsapi.org/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }
    }


}