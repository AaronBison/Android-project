package com.example.andoridproject.fragments.detail

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.andoridproject.R
import com.example.andoridproject.data.favorite.FavoriteViewModel
import com.example.andoridproject.data.favorite.MainScreenItem
import com.example.andoridproject.fragments.list.MainScreenFragmentDirections
import kotlinx.android.synthetic.main.fragment_detail_screen.*
import kotlinx.android.synthetic.main.fragment_detail_screen.view.*
import kotlinx.android.synthetic.main.fragment_modify.view.*

class DetailScreenFragment : Fragment() {

    private val args by navArgs<DetailScreenFragmentArgs>()
    private lateinit var mFavoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail_screen, container, false)
        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)


        Glide.with(this)
            .load(args.currentRestaurant.image_url)
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(view.detailImageView)
        view.detailNameTextView.setText(args.currentRestaurant.name)
        view.detailAddressTextView.setText(args.currentRestaurant.address)
        view.detailPriceTextView.setText(args.currentRestaurant.price)
        if (args.currentRestaurant.favorite == 1){
            view.favoriteButton.setText("Make Unfavorite")
            view.favoriteButton.setBackgroundColor(Color.BLACK)
            view.favoriteButton.setTextColor(Color.WHITE)
        }else{
            view.favoriteButton.setText("Make favorite")
            view.favoriteButton.setBackgroundColor(Color.WHITE)
            view.favoriteButton.setTextColor(Color.BLACK)
        }

        view.modifyButton.setOnClickListener{
            val action = DetailScreenFragmentDirections.actionFragmentDetailScreenToModifyFragment(args.currentRestaurant)
            Navigation.findNavController(view).navigate(action)
        }

        view.favoriteButton.setOnClickListener{
            if(args.currentRestaurant.favorite == 0){
                mFavoriteViewModel.addToFavorites(MainScreenItem(args.currentRestaurant.id,args.currentRestaurant.name,args.currentRestaurant.address,args.currentRestaurant.price,args.currentRestaurant.image_url, 1))
                view.favoriteButton.setText("Make Unfavorite")
                view.favoriteButton.setBackgroundColor(Color.BLACK)
                view.favoriteButton.setTextColor(Color.WHITE)
            }else{
                mFavoriteViewModel.deleteFromFavorites(args.currentRestaurant)
                view.favoriteButton.setText("Make favorite")
                view.favoriteButton.setBackgroundColor(Color.WHITE)
                view.favoriteButton.setTextColor(Color.BLACK)
            }
        }


        return view
    }
}