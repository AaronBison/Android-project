package com.example.andoridproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.andoridproject.R
import com.example.andoridproject.data.*
import kotlinx.android.synthetic.main.fragment_profile_screen.*

class ProfileScreenFragment : Fragment() {
   private lateinit var mUserViewModel: UserViewModel
   private lateinit var mFavoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_profile_screen, container, false)

        // Initialize UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        insertUserToDatabase()
        //---------------------------
        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        val restaurant = MainScreenItem(1,"Legjobb restaurant","Itt ne","Occsu","https://www.opentable.com/img/restimages/107257.jpg")
        mFavoriteViewModel.addToFavorites(restaurant)
        //---------------------------

        mUserViewModel.readUserData.observe(viewLifecycleOwner, Observer { user ->
            setUserLayout(user)
        })

        return view
    }

    private fun insertUserToDatabase() {
        mUserViewModel.addUser(Constants.getUser())
        Toast.makeText(requireContext(),"Welcome ${Constants.getUser().name}",Toast.LENGTH_LONG).show()
    }

    fun setUserLayout(user: List<User>){
        Log.e("Current user", "${user[0]}")
        usernameTextView.text = user[0].name
        adressTextView.text = user[0].address
        phoneNumberTextView.text = user[0].phone_number
        emailTextView.text = user[0].email
    }
}