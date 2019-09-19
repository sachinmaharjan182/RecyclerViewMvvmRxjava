package com.skilledhands.recyclerviewmvvmrxjava.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.skilledhands.recyclerviewmvvmrxjava.models.Post
import com.skilledhands.recyclerviewmvvmrxjava.models.Resource
import com.skilledhands.recyclerviewmvvmrxjava.repository.PostRepository

class PostsViewModel :ViewModel(){

    val postlist = MutableLiveData<Resource<ArrayList<Post>>>()

    fun getPosts(){
        val postRepository=PostRepository()
        postRepository.getMovies()
            .
    }
}