package com.example.mvvmdemo.network

import com.example.mvvmdemo.models.Feed
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("repositories")
    suspend fun getDataFromAPI(@Query("q") query: String): Feed
}