package com.example.coroutinecountdownviewdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author fanxiaoyang
 * date 2021/2/26
 * desc 分页加载viewModel
 */
class PageViewModel(context: Application):AndroidViewModel(context) {
    val userData = MutableLiveData<List<User>>()
    val database: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()

    val myPagingConfig = PagedList.Config.Builder()       // 分页设置
        .setPageSize(10)//一页多少个
        .setPrefetchDistance(5)
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(40)
        .build()
    val myConcertDataSource = MyDataSourceFactory(context)
    val concertList = LivePagedListBuilder(myConcertDataSource, myPagingConfig).build()

    fun makeData() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = FakeData.getData()
            database.userDao().deleteAll()
            repeat(data.size - 1) {
                val user = User(it, data[it], data[it])
                database.userDao().insertAll(user)
            }
        }
    }
}