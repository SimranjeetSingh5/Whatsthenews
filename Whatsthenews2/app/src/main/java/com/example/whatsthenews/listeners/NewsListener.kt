package com.example.whatsthenews.listeners

import com.example.whatsthenews.models.News

interface NewsListener {
    fun onNewsClicked(news: News?)
}