package com.example.mvvmdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.models.Items
import com.squareup.picasso.Picasso

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private var feeds = ArrayList<Items>()
    fun setUpdatedData(feeds: ArrayList<Items>) {
        this.feeds = feeds
        notifyDataSetChanged()
    }

    class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.img_feed_item)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title_feed_item)
        val tvDes = view.findViewById<TextView>(R.id.tv_description_feed_item)
        fun bind(item: Items) {
            tvTitle.text = item.name
            tvDes.text = item.description
            val url = item.owner.avatar_url
            Picasso.get()
                .load(url)
                .into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(feeds[position])
    }

    override fun getItemCount(): Int {
        return feeds.size
    }
}