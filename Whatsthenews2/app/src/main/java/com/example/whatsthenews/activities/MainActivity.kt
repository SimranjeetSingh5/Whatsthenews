package com.example.whatsthenews.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsthenews.adapter.MainAdapter
import com.example.whatsthenews.databinding.ActivityMainBinding
import com.example.whatsthenews.listeners.NewsListener
import com.example.whatsthenews.models.News
import com.example.whatsthenews.network.ApiService
import com.example.whatsthenews.repository.SearchNewsRepository
import com.example.whatsthenews.repository.TopHeadlinesRepository
import com.example.whatsthenews.util.ConnectionLiveData
import com.example.whatsthenews.viewmodel.SearchNewViewModel
import com.example.whatsthenews.viewmodel.SearchViewModelFactory
import com.example.whatsthenews.viewmodel.TopHeadlinesViewModel
import com.example.whatsthenews.viewmodel.TopHeadlinesViewModelFactory


class MainActivity : AppCompatActivity(),NewsListener{

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var topHeadlinesViewModel:TopHeadlinesViewModel
    private lateinit var topHeadlinesRepository: TopHeadlinesRepository
    private lateinit var searchNewViewModel:SearchNewViewModel
    private lateinit var searchNewsRepository:SearchNewsRepository
    private var apiService = ApiService.getInstance()
    private var news:MutableList<News> = ArrayList()
    private var searchResult:MutableList<News> = ArrayList()
    private lateinit var cld:ConnectionLiveData
    private lateinit var adapter:MainAdapter
    private lateinit var searchAdapter:MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        checkInternet()


    }
    private fun checkInternet() {
        cld = ConnectionLiveData(application)

        cld.observe(this, { isConnected ->

            if (isConnected){

                activityMainBinding.noInternetCL.visibility = View.INVISIBLE
                doInitialization()

            }else{
                activityMainBinding.topHeadlinesRV.visibility = View.INVISIBLE
                activityMainBinding.noInternetCL.visibility = View.VISIBLE
                activityMainBinding.noInternetButton?.setOnClickListener { checkInternet() }
            }

        })
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
        adapter = MainAdapter(news, this)
        activityMainBinding.topHeadlinesRV.adapter = adapter
        activityMainBinding.searchButton.setOnClickListener {
            val search = activityMainBinding.searchET.text.toString()
            searchTVShows(search)

        }

    }

    private fun searchTVShows(search:String) {
        searchNewsRepository = SearchNewsRepository(apiService)
        searchNewViewModel = SearchNewViewModel(searchNewsRepository)
        searchAdapter = MainAdapter(news, this)

        searchNewViewModel = ViewModelProvider(
            this, SearchViewModelFactory(SearchNewsRepository(apiService))).get(searchNewViewModel::class.java)

//        TODO->Testing of search
        searchNewViewModel.getSearchResult(search).observe(this,{
            searchResult.addAll(it.articles)
            searchAdapter.notifyDataSetChanged()
        })

        searchNewViewModel.getSearchResult(search)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        activityMainBinding.searchResultRV.visibility = View.VISIBLE
        activityMainBinding.topHeadlinesRV.visibility = View.GONE
        activityMainBinding.searchResultRV.layoutManager = llm
        activityMainBinding.searchResultRV.adapter = searchAdapter



    }

    private fun getTopHeadlines() {

        topHeadlinesViewModel.getTopHeadlines().observe(this, {
            if (it != null) {
                news.addAll(it.articles)
                adapter.notifyDataSetChanged()
            }


        })


        topHeadlinesViewModel.getTopHeadlines()

    }

    override fun onNewsClicked(news: News?) {

        val intent = Intent(this, NewsDetailActivity::class.java)
        intent.putExtra("currentNews", news)
        startActivity(intent)

    }



}