package com.example.mvvmdemo.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmdemo.models.Feed
import com.example.mvvmdemo.network.RetrofitInstance
import com.example.mvvmdemo.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private var feedLiveData: MutableLiveData<Feed> = MutableLiveData()

    fun getFeedLiveDataObserver(): MutableLiveData<Feed> {
        return feedLiveData
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
            val response = retrofitInstance.getDataFromAPI("ny")
            feedLiveData.postValue(response)
        }
    }

}