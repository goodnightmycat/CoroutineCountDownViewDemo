package com.example.coroutinecountdownviewdemo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
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
        coroutineScope?.launch(Dispatchers.Main) {
            repeat(REPEAT_COUNT) {
                delay(INTERVAL)
                val textString = "${context.getString(R.string.count_down_text)}${it}"
                textView.text = textString
            }
        }
    }

}