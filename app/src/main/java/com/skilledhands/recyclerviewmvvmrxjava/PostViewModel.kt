package com.skilledhands.recyclerviewmvvmrxjava

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketException

class PostViewModel : ViewModel() {

    val isBusy: ObservableBoolean= ObservableBoolean()
    val postList = MutableLiveData<ArrayList<Post>>()
    var errorMessage:String=""
    val isError=MutableLiveData<Boolean>()

    val compositeDisposable = CompositeDisposable()
    init {
        isBusy.set(false)
        isError.value=false
    }

    fun getPostList(){
        isBusy.set(true)
        isError.value=false
        val apiInterface=ApiClient.apiClient!!.create(ApiInterface::class.java)
        apiInterface.getPostList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Observer<ArrayList<Post>>{
                override fun onComplete() {
                    isBusy.set(false)

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: ArrayList<Post>) {
                    postList.value=t
                }

                override fun onError(e: Throwable) {
                    if (e is HttpException){
                        errorMessage= e.message()

                    }else if (e is SocketException){
                        errorMessage="Connection time out"

                    }else{
                        errorMessage="Network Error"
                    }

                    isBusy.set(false)
                    isError.value=true
                }

            })

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}