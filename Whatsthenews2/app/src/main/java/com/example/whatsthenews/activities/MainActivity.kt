package com.example.whatsthenews.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.whatsthenews.databinding.ActivityMainBinding
import com.example.whatsthenews.models.News
import com.example.whatsthenews.network.ApiService
import com.example.whatsthenews.repository.TopHeadlinesRepository
import com.example.whatsthenews.viewmodel.TopHeadlinesViewModel
import com.example.whatsthenews.viewmodel.TopHeadlinesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding:ActivityMainBinding
    private lateinit var topHeadlinesViewModel:TopHeadlinesViewModel
    private lateinit var topHeadlinesViewModelFactory: TopHeadlinesViewModelFactory
    private lateinit var topHeadlinesRepository: TopHeadlinesRepository
    private var apiService = ApiService.getInstance()
    private var news:MutableList<News> = ArrayList()

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
        topHeadlinesViewModel = ViewModelProvider(this,TopHeadlinesViewModelFactory(TopHeadlinesRepository(apiService))).get(topHeadlinesViewModel::class.java)

        getTopHeadlines()

    }

    private fun getTopHeadlines() {
//        topHeadlinesViewModel.newsList.observe(this, Observer {
//            Log.d("MainActivity","oncreate: $it")
//
//        })
//        topHeadlinesViewModel.errorMessage.observe(this, Observer {  })
        topHeadlinesViewModel.getTopHeadlines().observe(this,{
            Toast.makeText(applicationContext,"Total pages: ${it.totalResults}",Toast.LENGTH_SHORT).show()

        })
        topHeadlinesViewModel.getTopHeadlines()

    }

}