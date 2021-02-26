package com.example.coroutinecountdownviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_page.*

class PageActivity : AppCompatActivity() {
    val myAdapter = MyAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)
        initView()
    }

    private fun initView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@PageActivity)
            adapter = myAdapter
        }
        viewModel.concertList.observe(this, Observer {
            myAdapter.submitList(it)
        })

    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(PageViewModel::class.java)
    }

}