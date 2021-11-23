package com.example.whatsthenews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.whatsthenews.repository.TopHeadlinesRepository
import com.example.whatsthenews.responses.NewsResponse

class TopHeadlinesViewModel constructor(private val topHeadlinesRepository: TopHeadlinesRepository): ViewModel() {

    fun getTopHeadlines():LiveData<NewsResponse>{
        return topHeadlinesRepository.getTopHeadlines()
    }
}

