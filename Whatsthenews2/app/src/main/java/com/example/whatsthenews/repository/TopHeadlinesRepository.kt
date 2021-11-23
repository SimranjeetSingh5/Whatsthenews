package com.example.whatsthenews.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.whatsthenews.network.ApiClient
import com.example.whatsthenews.network.ApiService
import com.example.whatsthenews.responses.NewsResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class TopHeadlinesRepository constructor(private val retrofitService: ApiService) {

        fun  getTopHeadlines():LiveData<NewsResponse>{
                val data  = MutableLiveData<NewsResponse>()
                val errorMessage = MutableLiveData<String>()
                retrofitService.getTopHeadlines()?.enqueue(object : Callback, retrofit2.Callback<NewsResponse> {
                        override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                                data.value = response.body()
                        }

                        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                                errorMessage.postValue(t.message)
                        }

                })
                return data
        }

}