package com.skilledhands.recyclerviewmvvmrxjava.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skilledhands.recyclerviewmvvmrxjava.R
import com.skilledhands.recyclerviewmvvmrxjava.databinding.RowPostBinding
import com.skilledhands.recyclerviewmvvmrxjava.models.Post
import kotlinx.android.synthetic.main.row_post.view.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    var postList: ArrayList<Post> = ArrayList()

    fun updatePost(postList: ArrayList<Post>){
        this.postList=postList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {

        val layoutInflater=LayoutInflater.from(p0.context)
        val rowPostBinding:RowPostBinding=DataBindingUtil.inflate(layoutInflater,R.layout.row_post,p0,false)
        return PostViewHolder(rowPostBinding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
        var post=postList[p1]
        p0.bind(post)

    }


    class PostViewHolder(val binding: RowPostBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(post:Post){
            binding.post=post
            // forces the bindings to run immediately
            // instead of delaying them until the next frame
            binding.executePendingBindings()
        }
    }
}