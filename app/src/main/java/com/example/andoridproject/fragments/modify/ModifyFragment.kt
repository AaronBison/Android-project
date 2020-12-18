package com.example.andoridproject.fragments.modify

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.andoridproject.R
import com.example.andoridproject.data.favorite.FavoriteViewModel
import com.example.andoridproject.data.favorite.MainScreenItem
import kotlinx.android.synthetic.main.fragment_modify.*
import kotlinx.android.synthetic.main.fragment_modify.view.*


class ModifyFragment : Fragment() {

    private  lateinit var mFavoriteViewModel: FavoriteViewModel
    private val GALLERY_REQUEST_CODE = 123
    private lateinit var imageView: ImageView

    private val args by navArgs<ModifyFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_modify, container, false)

        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        Glide.with(this)
            .load(args.modifiableRestaurant.image_url)
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(view.modifyImageView)

        view.modifyNameEditText.setText(args.modifiableRestaurant.name)
        view.modifyAddressEditText.setText(args.modifiableRestaurant.address)
        view.modifyPriceEditText.setText(args.modifiableRestaurant.price)


        // Pick image from gallery
        imageView = view.findViewById(R.id.modifyImageView)
        view.changeImageButton.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Pick an image"),
                GALLERY_REQUEST_CODE
            )
        }

        view.applyButton.setOnClickListener {
            val modifiedRestaurant = updateRestaurant()

            val action = ModifyFragmentDirections.actionModifyFragmentToFragmentDetailScreen(
                modifiedRestaurant
            )
            Navigation.findNavController(view).navigate(action)
        }
        return view
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {

        // Set imageView after image picked
        if (requestCode == GALLERY_REQUEST_CODE && data != null) {
            val imageData = data.data
            imageView.setImageURI(imageData)
        }
    }

    private fun updateRestaurant(): MainScreenItem {

        val name = modifyNameEditText.text.toString()
        val address = modifyAddressEditText.text.toString()
        val price = modifyPriceEditText.text.toString()
        Log.e("price","$price")
        Log.e("inputCheck on price","${inputCheck(name, address, price)}")

//        val image_url = modifyImageView

//        Log.e("IMAGE","$image_url")
//        Log.e("ImageData", "$imageData")
        if (inputCheck(name, address, price)) {
            val updatedRestaurant = MainScreenItem(args.modifiableRestaurant.id, name, address, price, args.modifiableRestaurant.image_url,args.modifiableRestaurant.favorite, args.modifiableRestaurant.lat, args.modifiableRestaurant.lng)
            mFavoriteViewModel.updateFavorite(updatedRestaurant)
            Toast.makeText(requireContext(), "Updated Successfully!",Toast.LENGTH_LONG).show()
            return MainScreenItem(args.modifiableRestaurant.id, name, address, price, args.modifiableRestaurant.image_url,args.modifiableRestaurant.favorite, args.modifiableRestaurant.lat, args.modifiableRestaurant.lng)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields!",Toast.LENGTH_LONG).show()
            return MainScreenItem(args.modifiableRestaurant.id, args.modifiableRestaurant.name, args.modifiableRestaurant.address, args.modifiableRestaurant.price, args.modifiableRestaurant.image_url,args.modifiableRestaurant.favorite, args.modifiableRestaurant.lat, args.modifiableRestaurant.lng)
        }
    }

    private fun inputCheck(name: String, address: String, price: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(price))
    }
}