package com.example.andoridproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.Navigation
import com.example.andoridproject.R

class SplashScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        var containerLayouts = view.findViewById<LinearLayout>(R.id.containerLayout)
        containerLayouts.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_splashScreenFragment_to_mainScreenFragment)
        }

        return view
    }
}