package com.skilledhands.recyclerviewmvvmrxjava

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    fun getPostList():Observable<ArrayList<Post>>
}