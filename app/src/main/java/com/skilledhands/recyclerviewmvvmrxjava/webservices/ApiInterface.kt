package com.skilledhands.recyclerviewmvvmrxjava.webservices

import com.skilledhands.recyclerviewmvvmrxjava.models.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    fun getPostList():Observable<ArrayList<Post>>
}