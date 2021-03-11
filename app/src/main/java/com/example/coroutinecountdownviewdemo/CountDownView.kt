package com.example.coroutinecountdownviewdemo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

/**
 * Author:@FanXiaoYang
 * on 2021/2/25
 */
class CountDownView : FrameLayout {
    private val textView: TextView

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_count_down, this, false)
        textView = view.findViewById(R.id.textView)
        addView(view)
    }

    companion object {
        const val REPEAT_COUNT = 100
        const val INTERVAL = 1000L
    }

    fun startCountDown(coroutineScope: CoroutineScope? = null) {
        if (coroutineScope != null) {
            startInnerCountDown(coroutineScope)
        } else if (context is AppCompatActivity) {
            startInnerCountDown((context as? AppCompatActivity)?.lifecycleScope)
        }
    }

    private fun startInnerCountDown(coroutineScope: CoroutineScope?) {
        coroutineScope?.launch(Dispatchers.Main) {
            withTimeoutOrNull(5000) {
                repeat(10) {
                    Log.d("CountDownView", "startInnerCountDown: ${it + 1}")
                    delay(INTERVAL)
                }
            }
            repeat(REPEAT_COUNT) {
                val textString = "${context.getString(R.string.count_down_text)}${it + 1}"
                textView.text = textString
                delay(INTERVAL)
            }
        }
    }


}