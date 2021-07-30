package com.example.mvvmdemo.models

data class Feed(val items:ArrayList<Items>)
data class Items(val name:String,val description:String,val owner: Owner)
data class Owner(val avatar_url:String)