package com.example.mvvmdemo.network

import com.example.mvvmdemo.models.Country
import com.example.mvvmdemo.models.Feed
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("repositories")
    suspend fun getFeedDataFromAPI(@Query("q") query: String): Feed

    @GET("v2")
    suspend fun getCountryDataFromAPI(): Response<ArrayList<Country>>

    companion object {
        val feedBaseURL = "https://api.github.com/search/"
        val countryBaseURL = "https://restcountries.eu/rest/"
        var retrofitService: RetrofitService? = null
        fun getInstanceCountry(): RetrofitService {

            val retrofit = Retrofit.Builder()
                .baseUrl(countryBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(RetrofitService::class.java)

            return retrofitService!!
        }

        fun getInstanceFeed(): RetrofitService {

            val retrofit = Retrofit.Builder()
                .baseUrl(feedBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(RetrofitService::class.java)

            return retrofitService!!
        }
    }
}