package com.example.andoridproject.fragments.detail

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
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
import kotlin.properties.Delegates

class DetailScreenFragment : Fragment() {

    private val args by navArgs<DetailScreenFragmentArgs>()
    private lateinit var mFavoriteViewModel: FavoriteViewModel
    private var aFavorite by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail_screen, container, false)
        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        aFavorite = args.currentRestaurant.favorite

        Glide.with(this)
            .load(args.currentRestaurant.image_url)
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(view.detailImageView)
        view.detailNameTextView.setText(args.currentRestaurant.name)
        view.detailAddressTextView.setText(args.currentRestaurant.address)
        view.detailPriceTextView.setText(args.currentRestaurant.price)
        if (aFavorite == 1){
            view.favoriteButton.setText("Make Unfavorite")
            view.favoriteButton.setBackgroundColor(Color.BLACK)
            view.favoriteButton.setTextColor(Color.WHITE)
        }else{
            view.favoriteButton.setText("Make favorite")
            view.favoriteButton.setBackgroundColor(Color.WHITE)
            view.favoriteButton.setTextColor(Color.BLACK)
        }

        view.modifyButton.setOnClickListener{
            if(aFavorite == 1){
                val action = DetailScreenFragmentDirections.actionFragmentDetailScreenToModifyFragment(args.currentRestaurant)
                Navigation.findNavController(view).navigate(action)
            }else{
                Toast.makeText(requireContext(),"You can modify only favorite restaurants!",Toast.LENGTH_SHORT).show()
            }
        }

        view.favoriteButton.setOnClickListener{
            if(aFavorite == 0){
                aFavorite = 1
                mFavoriteViewModel.addToFavorites(MainScreenItem(args.currentRestaurant.id,args.currentRestaurant.name,args.currentRestaurant.address,args.currentRestaurant.price,args.currentRestaurant.image_url, aFavorite))
                view.favoriteButton.setText("Make Unfavorite")
                view.favoriteButton.setBackgroundColor(Color.BLACK)
                view.favoriteButton.setTextColor(Color.WHITE)
            }else{
                aFavorite = 0
                mFavoriteViewModel.deleteFromFavorites(args.currentRestaurant)
                view.favoriteButton.setText("Make favorite")
                view.favoriteButton.setBackgroundColor(Color.WHITE)
                view.favoriteButton.setTextColor(Color.BLACK)
            }
        }


        return view
    }
}