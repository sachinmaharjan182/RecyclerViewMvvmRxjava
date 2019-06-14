package com.skilledhands.recyclerviewmvvmrxjava

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_post.view.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    var postList: ArrayList<Post> = ArrayList()

    fun updatePost(postList: ArrayList<Post>){
        this.postList=postList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.row_post,null))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
        var post=postList[p1]
        p0.tvTitle.text=post.title
        p0.tvBody.text=post.body

    }


    class PostViewHolder(itemview:View): RecyclerView.ViewHolder(itemview) {
        var tvTitle=itemview.tv_title
        var tvBody=itemview.tv_body
    }
}