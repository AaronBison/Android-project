package com.example.andoridproject.fragments.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andoridproject.R
import com.example.andoridproject.data.favorite.FavoriteViewModel
import com.example.andoridproject.data.favorite.MainScreenItem
import com.example.andoridproject.data.user.Constants
import com.example.andoridproject.data.user.User
import com.example.andoridproject.data.user.UserViewModel
import com.example.andoridproject.fragments.list.MainScreenItemAdapter
import kotlinx.android.synthetic.main.fragment_main_screen.*
import kotlinx.android.synthetic.main.fragment_profile_screen.*
import kotlinx.android.synthetic.main.fragment_profile_screen.view.*

class ProfileScreenFragment : Fragment() {
   private lateinit var mUserViewModel: UserViewModel
   private lateinit var mFavoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_profile_screen, container, false)

        Toast.makeText(requireContext(),"Welcome ${view.usernameTextView.text}",Toast.LENGTH_SHORT).show()

        // Initialize UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // Initialize mFavoriteViewModel
        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)


//        insertUserToDatabase()

        mUserViewModel.readUserData.observe(viewLifecycleOwner, Observer { user ->
            setUserLayout(user)
        })

        mFavoriteViewModel.readFavoriteData.observe(viewLifecycleOwner, { list ->
            Log.e("LIST", "$list")
            profileRecyclerView.layoutManager = LinearLayoutManager(this.context)
            profileRecyclerView.setHasFixedSize(true)
            profileRecyclerView.adapter = view?.let { ProfileScreenFavoritesAdapter(list, it.context) }
        })

        return view
    }

    private fun insertUserToDatabase() {
        mUserViewModel.addUser(Constants.getUser())
    }

    private fun setUserLayout(user: List<User>){
        usernameTextView.text = user[0].name
        adressTextView.text = user[0].address
        phoneNumberTextView.text = user[0].phone_number
        emailTextView.text = user[0].email
    }
}