package com.example.whatsthenews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whatsthenews.repository.TopHeadlinesRepository
import java.lang.IllegalArgumentException

class TopHeadlinesViewModelFactory constructor(private val repository: TopHeadlinesRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TopHeadlinesViewModel::class.java)){
            TopHeadlinesViewModel(this.repository) as T
        }else
            throw IllegalArgumentException("ViewModel not found")
    }

}