package com.example.mvvmdemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCountryFragment()
    }

    private fun setupFeedFragment() {
        val feedFragment = FeedFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, feedFragment)
        fragmentTransaction.commit()
    }

    private fun setupCountryFragment() {
        val countryFragment = CountryFragment.newInstance(this)
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, countryFragment)
        fragmentTransaction.commit()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btnFeed -> {
                setupFeedFragment()
            }
            R.id.btnCountry -> {
                setupCountryFragment()
            }
        }
    }
}