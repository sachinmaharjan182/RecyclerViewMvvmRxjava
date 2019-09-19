package com.skilledhands.recyclerviewmvvmrxjava.repository

import com.skilledhands.recyclerviewmvvmrxjava.models.Post
import com.skilledhands.recyclerviewmvvmrxjava.models.Resource
import com.skilledhands.recyclerviewmvvmrxjava.webservices.ApiClient
import com.skilledhands.recyclerviewmvvmrxjava.webservices.ApiInterface
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostRepository {

    fun getMovies():Single<Resource<ArrayList<Post>>>{
        val apiInterface=ApiClient.apiClient!!.create(ApiInterface::class.java)
        val result= apiInterface.getPostList()
            .doOnSubscribe { it->CompositeDisposable().add(it) }
            .subscribe {
                ,
                ,

            }


    }




}