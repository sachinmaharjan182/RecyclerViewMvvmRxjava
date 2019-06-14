package com.skilledhands.recyclerviewmvvmrxjava

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.skilledhands.recyclerviewmvvmrxjava.databinding.ActivityPostBinding
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {


    lateinit var adapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postBinding: ActivityPostBinding= DataBindingUtil.setContentView(this,R.layout.activity_post)

        val postViewModel=ViewModelProviders.of(this).get(PostViewModel::class.java)


        postBinding.lifecycleOwner=this
        postBinding.postviewmodel=postViewModel

        initRecyclerview()

        bt_fetch.setOnClickListener{
            postViewModel.getPostList()
        }

        postViewModel.postList.observe(this, Observer {
            if (it != null) {
                adapter.updatePost(it)
            }

        })

        postViewModel.isError.observe(this, Observer { error ->
            if (error!!){
                showToast(postViewModel.errorMessage)
            }
        })

    }


    private fun initRecyclerview() {
        adapter= PostAdapter()
        rv_post.setHasFixedSize(true)
        rv_post.layoutManager=LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        rv_post.adapter=adapter
    }
}
