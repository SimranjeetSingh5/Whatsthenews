package com.example.whatsthenews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whatsthenews.models.News
import com.example.whatsthenews.repository.TopHeadlinesRepository
import com.example.whatsthenews.responses.NewsResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class TopHeadlinesViewModel constructor(private val topHeadlinesRepository: TopHeadlinesRepository): ViewModel() {

    fun getTopHeadlines():LiveData<NewsResponse>{
        return topHeadlinesRepository.getTopHeadlines()
    }
}

