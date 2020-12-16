package com.example.andoridproject.fragments.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andoridproject.R
import com.example.andoridproject.data.favorite.MainScreenItem
import kotlinx.android.synthetic.main.recycler_view_main_screen_item.view.*
import kotlinx.android.synthetic.main.recylcer_view_profile_item.view.*

class ProfileScreenFavoritesAdapter(private val profileScreenList: List<MainScreenItem>, private val context: Context): RecyclerView.Adapter<ProfileScreenFavoritesAdapter.ProfileScreenFavoritesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileScreenFavoritesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recylcer_view_profile_item, parent, false)
        return ProfileScreenFavoritesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileScreenFavoritesViewHolder, position: Int) {
        val currentItem = profileScreenList[position]

        Glide.with(context)
            .load(currentItem.image_url)
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(holder.profileRestaurantImageView)

        holder.profileRestaurantNameTextView.text = currentItem.name
    }

    override fun getItemCount() = profileScreenList.size

    class ProfileScreenFavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileRestaurantImageView: ImageView = itemView.profileRestaurantImageView
        val profileRestaurantNameTextView: TextView = itemView.profileRestaurantNameTextView
    }
}