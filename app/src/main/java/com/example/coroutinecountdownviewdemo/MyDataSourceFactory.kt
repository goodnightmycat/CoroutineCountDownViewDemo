package com.example.coroutinecountdownviewdemo

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.paging.DataSource
import androidx.room.Room
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MyDataSourceFactory(val context: Application) : DataSource.Factory<Int, User>() {
    val database: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()

    override fun create(): DataSource<Int, User> {
        return DataSource()
    }

    inner class DataSource<T, U> : androidx.paging.PageKeyedDataSource<Int, User>() {
        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, User>
        ) {
            val list = ArrayList<User>()
            repeat(20) {
                list.add(
                    database.userDao()
                        .getUserById(Random(it).nextInt(0, FakeData.getData().size - 1))
                )
            }
            callback.onResult(list, 0, 1)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
            Log.d("MyDataSourceFactory", "loadAfter: ${params.key}")
            val list = ArrayList<User>()
            repeat(20) {
                list.add(
                    database.userDao()
                        .getUserById(Random(it).nextInt(0, FakeData.getData().size - 1))
                )
            }
            callback.onResult(list, params.key)
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {

        }

    }

}