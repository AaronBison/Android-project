package com.example.andoridproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_main_screen_item.view.*

class MainScreenItemAdapter(private val mainScreenList: List<MainScreenItem>) : RecyclerView.Adapter<MainScreenItemAdapter.MainScreenItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_main_screen_item, parent, false)

        return MainScreenItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainScreenItemViewHolder, position: Int) {
        val currentItem = mainScreenList[position]

        holder.restaurantImageView.setImageResource(currentItem.imageResource)
        holder.restaurantNameTextView.text = currentItem.title
        holder.restaurantAddressTextView.text = currentItem.address
        holder.restaurantPriceTextView.text = currentItem.price
//        holder.favoriteButton.setImageResource(currentItem.imageResource)
    }

    override fun getItemCount() = mainScreenList.size

    class MainScreenItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val restaurantImageView: ImageView = itemView.restaurantImageView
        val restaurantNameTextView: TextView = itemView.restaurantNameTextView
        val restaurantAddressTextView: TextView = itemView.restaurantAddressTextView
        val restaurantPriceTextView: TextView = itemView.restaurantPriceTextView
        val favoriteButton: ImageButton = itemView.favoriteButton
    }
}