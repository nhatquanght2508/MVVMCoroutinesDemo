package com.example.mvvmdemo.Repository

import com.example.mvvmdemo.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllCountry() = retrofitService.getCountryDataFromAPI()
    suspend fun getAllFeed() = retrofitService.getFeedDataFromAPI("ny")
}