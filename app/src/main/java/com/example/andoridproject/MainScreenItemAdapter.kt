package com.example.andoridproject

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.recycler_view_main_screen_item.view.*

class MainScreenItemAdapter(private val mainScreenList: MainScreenItemList, private val context: Context) : RecyclerView.Adapter<MainScreenItemAdapter.MainScreenItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_main_screen_item, parent, false)

        return MainScreenItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainScreenItemViewHolder, position: Int) {
        val currentItem = mainScreenList.restaurants[position]

        Glide.with(context)
            .load(currentItem.image_url)
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(holder.restaurantImageView)

        holder.restaurantNameTextView.text = currentItem.name
        holder.restaurantAddressTextView.text = currentItem.address
        holder.restaurantPriceTextView.text = currentItem.price
    }

    override fun getItemCount() = mainScreenList.restaurants.size

    class MainScreenItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val restaurantImageView: ImageView = itemView.restaurantImageView
        val restaurantNameTextView: TextView = itemView.restaurantNameTextView
        val restaurantAddressTextView: TextView = itemView.restaurantAddressTextView
        val restaurantPriceTextView: TextView = itemView.restaurantPriceTextView
    }
}