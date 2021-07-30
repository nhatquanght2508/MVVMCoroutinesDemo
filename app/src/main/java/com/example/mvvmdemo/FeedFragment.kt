package com.example.mvvmdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.ViewModel.MainActivityViewModel
import com.example.mvvmdemo.adapter.FeedAdapter
import com.example.mvvmdemo.models.Feed

/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedFragment : Fragment() {

    lateinit var feedAdapter: FeedAdapter
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
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getFeedLiveDataObserver().observe(viewLifecycleOwner, {
            if (it != null) {
                feedAdapter.setUpdatedData(it.items)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FeedFragment().apply {
            }
    }
}