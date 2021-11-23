package com.example.whatsthenews.network

import android.content.Context
import com.example.whatsthenews.util.Constant
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private var retrofit: Retrofit? = null
    private var api = Constant.API_KEY

    fun getRetrofit():Retrofit?{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
    }
        return retrofit
    }
}