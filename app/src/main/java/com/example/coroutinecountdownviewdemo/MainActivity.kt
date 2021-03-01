package com.example.coroutinecountdownviewdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private fun startCountDown() {
        countDownView.startCountDown()
    }

}