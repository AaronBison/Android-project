package com.example.andoridproject.fragments.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.andoridproject.R

class SplashScreenFragment : Fragment() {

    companion object {

        // Delayer function
        fun after(delay: Long, process: () -> Unit) {
            Handler().postDelayed({
                process()
            }, delay)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        after(3000) {
            Navigation.findNavController(view).navigate(R.id.action_splashScreenFragment_to_mainScreenFragment)
        }

        return view
    }
}