package com.example.mvvmdemo.adapter

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.models.Country
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class CountryAdapter(private val activity: Activity) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private var countries = ArrayList<Country>()

    fun setUpdatedData(countries: ArrayList<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgFlag = view.findViewById<ImageView>(R.id.img_flag_item)
        val tvName = view.findViewById<TextView>(R.id.tv_name_country_item)
        val tvCapital = view.findViewById<TextView>(R.id.tv_capital_country_item)
        val tvRegion = view.findViewById<TextView>(R.id.tv_region_country_item)
        val tvPopular = view.findViewById<TextView>(R.id.tv_population_country_item)

        fun bind(country: Country, activity: Activity) {
            tvName.text = activity.getString(R.string.name, country.name, country.alpha2Code)
            tvCapital.text = activity.getString(R.string.capital, country.capital)
            tvRegion.text = activity.getString(R.string.region, country.region)
//            tvPopular.text = activity.getString(R.string.region, country.population)
            GlideToVectorYou.justLoadImage(activity, Uri.parse(country.flag), imgFlag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position], activity)
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}