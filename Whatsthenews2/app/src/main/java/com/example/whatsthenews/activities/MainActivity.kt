package com.example.whatsthenews.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsthenews.adapter.MainAdapter
import com.example.whatsthenews.databinding.ActivityMainBinding
import com.example.whatsthenews.models.News
import com.example.whatsthenews.network.ApiService
import com.example.whatsthenews.repository.TopHeadlinesRepository
import com.example.whatsthenews.viewmodel.TopHeadlinesViewModel
import com.example.whatsthenews.viewmodel.TopHeadlinesViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding:ActivityMainBinding
    private lateinit var topHeadlinesViewModel:TopHeadlinesViewModel
    private lateinit var topHeadlinesRepository: TopHeadlinesRepository
    private var apiService = ApiService.getInstance()
    private var news:MutableList<News> = ArrayList()
    private lateinit var newsSources:String
    lateinit var adapter:MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        doInitialization()

    }

    private fun doInitialization() {
        activityMainBinding.topHeadlinesRV.setHasFixedSize(true)
        topHeadlinesRepository = TopHeadlinesRepository(apiService)
        topHeadlinesViewModel = TopHeadlinesViewModel(topHeadlinesRepository)
        topHeadlinesViewModel = ViewModelProvider(
            this, TopHeadlinesViewModelFactory(
                TopHeadlinesRepository(
                    apiService
                )
            )
        ).get(topHeadlinesViewModel::class.java)

        getTopHeadlines()
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        activityMainBinding.topHeadlinesRV.layoutManager = llm
        adapter = MainAdapter(news)
        activityMainBinding.topHeadlinesRV.adapter = adapter



    }

    private fun getTopHeadlines() {

        topHeadlinesViewModel.getTopHeadlines().observe(this, {
            if (it!=null){
                news.addAll(it.articles)
                adapter.notifyDataSetChanged()
            }



        })


        topHeadlinesViewModel.getTopHeadlines()

    }

}