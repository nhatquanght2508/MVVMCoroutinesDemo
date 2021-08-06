package com.example.mvvmdemo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmdemo.models.Countries
import com.example.mvvmdemo.models.Country
import com.example.mvvmdemo.models.Feed
import com.example.mvvmdemo.network.RetrofitInstance
import com.example.mvvmdemo.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private var feedLiveData: MutableLiveData<Feed> = MutableLiveData()
    private var countryLiveData: MutableLiveData<Country> = MutableLiveData()

    fun getFeedLiveDataObserver(): MutableLiveData<Feed> {
        return feedLiveData
    }

    fun getCountryLiveDataObserver(): MutableLiveData<Country> {
        return countryLiveData
    }

    fun feedApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance =
                RetrofitInstance.getFeedRetrofitInstance().create(RetrofitService::class.java)
            val response = retrofitInstance.getFeedDataFromAPI("ny")
            feedLiveData.postValue(response)
        }
    }

    fun countryApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance =
                RetrofitInstance.getCountryRetrofitInstance().create(RetrofitService::class.java)
            val response = retrofitInstance.getCountryDataFromAPI()
            countryLiveData.postValue(response.items[0])
        }
    }

}