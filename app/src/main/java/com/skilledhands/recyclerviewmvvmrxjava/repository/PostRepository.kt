package com.skilledhands.recyclerviewmvvmrxjava.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.skilledhands.recyclerviewmvvmrxjava.models.Post
import com.skilledhands.recyclerviewmvvmrxjava.models.Resource
import com.skilledhands.recyclerviewmvvmrxjava.webservices.ApiClient
import com.skilledhands.recyclerviewmvvmrxjava.webservices.ApiInterface
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PostRepository {

    val TAG=this.javaClass.simpleName
    val compositeDisposable = CompositeDisposable()

    fun getPosts(): MutableLiveData<Resource<ArrayList<Post>>> {
        Log.d(TAG,"start of api")
        val apiInterface=ApiClient.apiClient!!.create(ApiInterface::class.java)
        var data = MutableLiveData<Resource<ArrayList<Post>>>()
        data.value= Resource.loading(null)
        compositeDisposable.add(
            apiInterface.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response->
                    Log.d(TAG,"success data")
                    data.value = Resource.success(response)

                },{ error->
                    Log.d(TAG,"error data")
                    data.value = Resource.error(error.localizedMessage.toString(),null)
                })
        )
        Log.d(TAG,"return data")
        return data
    }

    fun dispose(){
        compositeDisposable.clear()
    }

}