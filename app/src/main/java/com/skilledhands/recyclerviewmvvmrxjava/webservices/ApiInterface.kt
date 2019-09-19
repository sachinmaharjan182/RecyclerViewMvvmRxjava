package com.skilledhands.recyclerviewmvvmrxjava.webservices

import com.skilledhands.recyclerviewmvvmrxjava.models.Post
import com.skilledhands.recyclerviewmvvmrxjava.models.Resource
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    fun getPostList():Observable<ArrayList<Post>>


    @GET("posts")
    fun getPosts():Single<Resource<ArrayList<Post>>>

}