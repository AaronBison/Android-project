package com.example.andoridproject.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andoridproject.R
import com.example.andoridproject.data.favorite.FavoriteViewModel
import com.example.andoridproject.data.favorite.MainScreenItem
import kotlinx.android.synthetic.main.recycler_view_main_screen_item.view.*

class MainScreenItemAdapter(private val mainScreenList: List<MainScreenItem>, private val context: Context) : RecyclerView.Adapter<MainScreenItemAdapter.MainScreenItemViewHolder>(){

    private  lateinit var mFavoriteViewModel: FavoriteViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_main_screen_item, parent, false)

        return MainScreenItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainScreenItemViewHolder, position: Int) {
        val currentItem = mainScreenList[position]

        Glide.with(context)
            .load(currentItem.image_url)
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(holder.restaurantImageView)

        holder.restaurantNameTextView.text = currentItem.name
        holder.restaurantAddressTextView.text = currentItem.address
        holder.restaurantPriceTextView.text = currentItem.price
        if(currentItem.favorite == 1){
            holder.itemView.favriteImageView.setBackgroundResource(R.drawable.ic_baseline_red_favorite)
        }else{
            holder.itemView.favriteImageView.setBackgroundResource(R.drawable.ic_baseline_shadow_favorite)
        }

        // Navigate to Detail Fragment when clicked on item
        holder.itemView.rowLayout.setOnClickListener{
            val action = MainScreenFragmentDirections.actionMainScreenFragmentToDetailScreenFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = mainScreenList.size

    class MainScreenItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val restaurantImageView: ImageView = itemView.restaurantImageView
        val restaurantNameTextView: TextView = itemView.restaurantNameTextView
        val restaurantAddressTextView: TextView = itemView.restaurantAddressTextView
        val restaurantPriceTextView: TextView = itemView.restaurantPriceTextView
    }

}