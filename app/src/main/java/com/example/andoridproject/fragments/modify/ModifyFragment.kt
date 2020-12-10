package com.example.andoridproject.fragments.modify

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.andoridproject.R
import kotlinx.android.synthetic.main.fragment_detail_screen.view.*
import kotlinx.android.synthetic.main.fragment_modify.view.*

class ModifyFragment : Fragment() {

    private val args by navArgs<ModifyFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_modify, container, false)

        Glide.with(this)
            .load(args.modifiableRestaurant.image_url)
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(view.modifyImageView)
        view.modifyNameEditText.setText(args.modifiableRestaurant.name)
        view.modifyAddressEditText.setText(args.modifiableRestaurant.address)
        view.modifyPriceEditText.setText(args.modifiableRestaurant.price)


        view.applyButton.setOnClickListener{
            val action = ModifyFragmentDirections.actionModifyFragmentToFragmentDetailScreen(args.modifiableRestaurant)
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }
}