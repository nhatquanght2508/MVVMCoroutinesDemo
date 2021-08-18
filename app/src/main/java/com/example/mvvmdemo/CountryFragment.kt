package com.example.mvvmdemo

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.Repository.MainRepository
import com.example.mvvmdemo.ViewModel.MainViewModel
import com.example.mvvmdemo.ViewModel.MainViewModelFactory
import com.example.mvvmdemo.adapter.CountryAdapter
import com.example.mvvmdemo.network.RetrofitService

class CountryFragment(private val activity: Activity) : Fragment() {
    lateinit var countryAdapter: CountryAdapter
    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country, container, false)
        initAdapter(view)
        initViewModel()
        return view
    }

    private fun initAdapter(view: View?) {
        val rcvCountry = view?.findViewById<RecyclerView>(R.id.rcv_country)
        rcvCountry?.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        rcvCountry?.addItemDecoration(decoration)
        countryAdapter = CountryAdapter(activity)
        rcvCountry?.adapter = countryAdapter
    }

    private fun initViewModel() {
        val retrofitService = RetrofitService.getInstanceCountry()
        val mainRepository = MainRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)
        viewModel.countryList.observe(viewLifecycleOwner, {
            countryAdapter.setUpdatedData(it)
        })
        viewModel.errorMes.observe(viewLifecycleOwner,{
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.loading.observe(viewLifecycleOwner,{
            if (it){
            }
        })
        viewModel.getAllCountry()
    }

    companion object {
        @JvmStatic
        fun newInstance(activity: Activity) =
            CountryFragment(activity).apply {
                arguments = Bundle().apply {
                }
            }
    }
}