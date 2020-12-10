package com.example.andoridproject.fragments.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.andoridproject.R
import com.example.andoridproject.fragments.list.MainScreenFragmentDirections
import kotlinx.android.synthetic.main.fragment_detail_screen.*
import kotlinx.android.synthetic.main.fragment_detail_screen.view.*
import kotlinx.android.synthetic.main.fragment_modify.view.*

class DetailScreenFragment : Fragment() {

    private val args by navArgs<DetailScreenFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail_screen, container, false)


        Glide.with(this)
            .load(args.currentRestaurant.image_url)
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(view.detailImageView)
        view.detailNameTextView.setText(args.currentRestaurant.name)
        view.detailAddressTextView.setText(args.currentRestaurant.address)
        view.detailPriceTextView.setText(args.currentRestaurant.price)

        view.modifyButton.setOnClickListener{
            val action = DetailScreenFragmentDirections.actionFragmentDetailScreenToModifyFragment(args.currentRestaurant)
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }
}