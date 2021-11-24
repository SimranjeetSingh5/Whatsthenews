package com.example.whatsthenews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsthenews.databinding.ListItemBinding
import com.example.whatsthenews.listeners.NewsListener
import com.example.whatsthenews.models.News
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class MainAdapter(var news: MutableList<News>?, private val newsListener: NewsListener):
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ListItemBinding, private val newsListener: NewsListener) :
        RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {

        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding,newsListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder){
            with(news?.get(position)){

                binding.root.setOnClickListener {
                    newsListener.onNewsClicked(news!!.elementAt(position))
                }
                binding.newSource.text = this?.source?.name
                binding.title.text = this?.title
                binding.date.text = this?.publishedAt
                Picasso.get().load(this?.urlToImage).fit().centerCrop().noFade().into(
                    binding.imageNews,
                    object : Callback {
                        override fun onSuccess() {
                            binding.imageNews.animate().setDuration(300).alpha(1f).start()
                        }

                        override fun onError(e: Exception?) {}
                    })
            }
        }
    }

    override fun getItemCount(): Int {
        return news!!.size
    }



}




