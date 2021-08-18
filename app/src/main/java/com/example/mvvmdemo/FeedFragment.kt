package com.example.mvvmdemo

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
import com.example.mvvmdemo.adapter.FeedAdapter
import com.example.mvvmdemo.network.RetrofitService

/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedFragment : Fragment() {

    lateinit var feedAdapter: FeedAdapter
    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        initAdapter(view)
        initViewModel()
        return view
    }

    private fun initAdapter(view: View?) {
        val rcvFeed = view?.findViewById<RecyclerView>(R.id.rcv_feed)
        rcvFeed?.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        rcvFeed?.addItemDecoration(decoration)
        feedAdapter = FeedAdapter()
        rcvFeed?.adapter = feedAdapter
    }

    private fun initViewModel() {
        val retrofitService = RetrofitService.getInstanceFeed()
        val mainRepository = MainRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)
        viewModel.feedList.observe(viewLifecycleOwner, {
            feedAdapter.setUpdatedData(it.items)
        })

        viewModel.errorMes.observe(viewLifecycleOwner,{
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.loading.observe(viewLifecycleOwner,{
            if (it){
            }
        })
        viewModel.getAllFeed()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FeedFragment().apply {
            }
    }
}