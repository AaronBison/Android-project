package com.example.andoridproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.andoridproject.R
import com.example.andoridproject.data.Constants
import com.example.andoridproject.data.UserViewModel

class ProfileScreenFragment : Fragment() {
   private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_profile_screen, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        insertUserToDatabase()

        return view
    }

    private fun insertUserToDatabase() {
        mUserViewModel.addUser(Constants.getUser())
        Toast.makeText(requireContext(),"Welcome ${Constants.getUser().name}",Toast.LENGTH_LONG).show()
    }
}