package com.example.coroutinecountdownviewdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

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
        button.setOnClickListener {
            lifecycleScope.launch {
                getDifferentData()
            }
        }
    }

    private fun startCountDown() {
        countDownView.startCountDown()
    }

    /**
     * 组合挂起函数模拟不同接口请求的异步返回
     */
    private suspend fun getDifferentData() {
        lifecycleScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val response = MergeResponse()
                val courseName = async { getCourseName() }
                val studentName = async { getStudentName() }
                response.courseName = courseName.await()
                response.studentName = studentName.await()
            }
            Log.d(
                "getDifferentData",
                "costTime:${time} ->workingThread:${Thread.currentThread().name}"
            )
        }
    }

    private suspend fun getStudentName(): String {
        delay(1000)
        return "xiaoyang"
    }

    private suspend fun getCourseName(): String {
        delay(5000)
        return "kotlin"
    }

    inner class MergeResponse {
        var courseName: String? = null
        var studentName: String? = null
    }

}