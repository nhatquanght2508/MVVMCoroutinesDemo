package com.example.mvvmdemo.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.Repository.MainRepository
import com.example.mvvmdemo.models.Country
import com.example.mvvmdemo.models.Feed
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMes = MutableLiveData<String>()
    val countryList = MutableLiveData<ArrayList<Country>>()
    var feedList = MutableLiveData<Feed>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()
    fun getAllCountry() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllCountry()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countryList.postValue(response.body())
                    loading.postValue(false)
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    fun getAllFeed(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllFeed()
            withContext(Dispatchers.Main){
                feedList.postValue(response)
                loading.postValue(false)
//                if (response.isSuccessful){
//                    feedList.postValue(response)
//                    loading.value = false
//                } else{
//                    onError("Error: ${response.message()}")
//                }
            }
        }
    }


    private fun onError(message: String) {
        errorMes.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}