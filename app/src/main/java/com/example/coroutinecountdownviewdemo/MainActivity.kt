package com.example.coroutinecountdownviewdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        countDownView.setOnClickListener {
            startCountDown()
        }
        countDownView.setOnLongClickListener {
            val intent = Intent(this, PageActivity::class.java)
            startActivity(intent)
            true
        }

    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun startCountDown() {
        countDownView.startCountDown(viewModel.viewModelScope)
    }

}