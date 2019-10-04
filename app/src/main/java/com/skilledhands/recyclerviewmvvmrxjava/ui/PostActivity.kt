package com.skilledhands.recyclerviewmvvmrxjava.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.skilledhands.recyclerviewmvvmrxjava.viewmodels.PostViewModel
import com.skilledhands.recyclerviewmvvmrxjava.R
import com.skilledhands.recyclerviewmvvmrxjava.databinding.ActivityPostBinding
import com.skilledhands.recyclerviewmvvmrxjava.models.Status
import com.skilledhands.recyclerviewmvvmrxjava.utils.showToast
import com.skilledhands.recyclerviewmvvmrxjava.viewmodels.PostsViewModel
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {


    lateinit var adapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postBinding: ActivityPostBinding= DataBindingUtil.setContentView(this,
            R.layout.activity_post
        )

        val postViewModel=ViewModelProviders.of(this).get(PostsViewModel::class.java)
        postBinding.lifecycleOwner=this

        initRecyclerview()

        postViewModel.getPost().observe(this, Observer { resource->
            Log.d("PostActivity","onchanged")
            when(resource!!.status){
                Status.LOADING -> {
                    Log.d("PostActivity","Loading")
                    showProgressBar()
                }
                Status.ERROR -> {
                    Log.d("PostActivity","error")
                    resource.message?.let { showToast(it) }
                    hideProgressBar()
                }
                Status.SUCCESS -> {
                    Log.d("PostActivity","successs")
                    hideProgressBar()
                    resource.data?.let { adapter.updatePost(it) }
                }
                else->{
                    Log.d("PostActivity","ELSE")

                }

            }

        })


        bt_fetch.setOnClickListener{

            postViewModel.getPosts()

        }


//        postViewModel.isError.observe(this, Observer { error ->
//            if (error!!){
//                showToast(postViewModel.errorMessage)
//            }
//        })

    }

    private fun showProgressBar() {
        pb_fetch.visibility=View.VISIBLE
    }

    private fun hideProgressBar(){
        pb_fetch.visibility=View.GONE
    }


    private fun initRecyclerview() {
        adapter= PostAdapter()
        rv_post.setHasFixedSize(true)
        rv_post.layoutManager=LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        rv_post.adapter=adapter
    }

}
