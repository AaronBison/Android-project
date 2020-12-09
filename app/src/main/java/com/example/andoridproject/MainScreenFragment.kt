package com.example.andoridproject

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_main_screen.*
import okhttp3.*
import java.io.IOException


class MainScreenFragment : Fragment() {

    private lateinit var mRequestResultTextView: TextView
    private val mHandler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Creates the recycler view from the list of items
        LoadRecyclerView()
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)

//        val handler = Handler()
//        handler.postDelayed({view?.let { Navigation.findNavController(it).navigate(R.id.action_navigation_home_to_fragment_splash_screen) }},3000)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    fun LoadRecyclerView() {

        // Specifying the url for the request
        val url = "https://opentable.herokuapp.com/api/restaurants?city=Chicago"
        // Setting up a request variable for the specified url
        val request = Request.Builder().url(url).build()
        // Creating a new OkHttpClient
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {


            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
//                Log.e("Success", "Successful OkHttp request: $body")

                // Setting up a gson variable
                val gson = GsonBuilder().create()

                val mainScreenList = gson.fromJson(body, MainScreenItemList::class.java)
//                Log.e("Success", "Successful Gson operation: ${mainScreenList.restaurants[0].name}")

                mHandler.post {
                    recyclerView.adapter = MainScreenItemAdapter(mainScreenList, view!!.context)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Failure", "Failed OkHttp request")
            }
        })
    }
}