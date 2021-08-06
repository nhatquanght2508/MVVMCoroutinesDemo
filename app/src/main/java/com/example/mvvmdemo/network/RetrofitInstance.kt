package com.example.mvvmdemo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        val feedBaseURL = "https://api.github.com/search/"
        val countryBaseURL = "https://restcountries.eu/rest/"

        fun getFeedRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(feedBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getCountryRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(countryBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }


}