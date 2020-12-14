package com.example.andoridproject.fragments.modify

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.core.provider.FontsContractCompat.FontRequestCallback.RESULT_OK
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.andoridproject.R
import kotlinx.android.synthetic.main.fragment_modify.*
import kotlinx.android.synthetic.main.fragment_modify.view.*


class ModifyFragment : Fragment() {

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
        val view =  inflater.inflate(R.layout.fragment_modify, container, false)

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

        view.applyButton.setOnClickListener{
            updateRestaurant()

            val action = ModifyFragmentDirections.actionModifyFragmentToFragmentDetailScreen(args.modifiableRestaurant)
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
            val imageData: Uri? = data.data
            imageView.setImageURI(imageData)
        }
    }

    private fun updateRestaurant(){
        val name = modifyNameEditText.text
        val address = modifyAddressEditText.text
        val price = modifyPriceEditText.text

//        val image_url = modifyImageView

//        Log.e("IMAGE","$image_url")

    }
}