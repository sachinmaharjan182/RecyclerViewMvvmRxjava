package com.skilledhands.recyclerviewmvvmrxjava.viewmodels

import android.arch.lifecycle.*
import android.util.Log
import com.skilledhands.recyclerviewmvvmrxjava.models.Post
import com.skilledhands.recyclerviewmvvmrxjava.models.Resource
import com.skilledhands.recyclerviewmvvmrxjava.repository.PostRepository
import io.reactivex.disposables.CompositeDisposable

class PostsViewModel :ViewModel(){
    val btnClicked = MutableLiveData<Boolean>()
    val compositeDisposable = CompositeDisposable()
    val postRepository = PostRepository()

  /*  val postList:LiveData<Resource<ArrayList<Post>>> = Transformations.switchMap(btnClicked) { boolean->
        postRepository.getPosts()

    }*/
    val postList = MediatorLiveData<Resource<ArrayList<Post>>>()



    /*fun getPosts(){
        Log.d("Viewmodel","getpost triggered")
        postList = Transformations.map()
    }*/
    fun getPosts(){
        Log.d("Viewmodel","getpost triggered")
        postList.addSource(postRepository.getPosts()){ data->
            postList.value = data
        }
    }

    fun getPost(): LiveData<Resource<ArrayList<Post>>> {
        return this.postList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        postRepository.dispose()

    }
}