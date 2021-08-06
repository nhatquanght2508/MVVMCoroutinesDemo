package com.example.mvvmdemo.network

import com.example.mvvmdemo.models.Countries
import com.example.mvvmdemo.models.Country
import com.example.mvvmdemo.models.Feed
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("repositories")
    suspend fun getFeedDataFromAPI(@Query("q") query: String): Feed

    @GET("v2")
    suspend fun getCountryDataFromAPI(): Countries
}