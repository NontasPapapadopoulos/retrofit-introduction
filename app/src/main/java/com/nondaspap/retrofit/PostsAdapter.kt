package com.nondaspap.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nondaspap.retrofit.databinding.PostItemBinding

class PostsAdapter(private var posts: ArrayList<Post>): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {


    inner class PostsViewHolder(val adapterBinding: PostItemBinding)
        : RecyclerView.ViewHolder(adapterBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PostsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.adapterBinding.userIdTextView.text = posts[position].userId.toString()
        holder.adapterBinding.idTextView.text = posts[position].id.toString()
        holder.adapterBinding.titleTextView.text = posts[position].title
        holder.adapterBinding.bodyTextView.text = posts[position].subtitle

    }

    override fun getItemCount(): Int {
        return posts.size
    }


}