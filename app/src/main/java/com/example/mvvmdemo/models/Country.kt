package com.example.mvvmdemo.models

data class Countries(val items:ArrayList<Country>)
data class Country(
    val name: String?,
    val alpha2Code: String?,
    val capital: String?,
    val region: String?,
    val flag: String?,

)
