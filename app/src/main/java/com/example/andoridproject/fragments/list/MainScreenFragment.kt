package com.example.andoridproject.fragments.list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andoridproject.R
import com.example.andoridproject.data.favorite.FavoriteViewModel
import com.example.andoridproject.data.favorite.MainScreenItem
import com.example.andoridproject.data.favorite.MainScreenItemList
import kotlinx.android.synthetic.main.fragment_main_screen.*


class MainScreenFragment : Fragment() {

    private lateinit var mRequestResultTextView: TextView
    private val mHandler: Handler = Handler(Looper.getMainLooper())
    private lateinit var mFavoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Creates the recycler view from the list of items
        // LoadRecyclerView()

        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        mFavoriteViewModel.readFavoriteData.observe(viewLifecycleOwner,{ MainScreenItem ->
            recyclerView.adapter = view?.let { MainScreenItemAdapter(MainScreenItem, it.context) }
        })

        recyclerView.layoutManager = LinearLayoutManager(this.context)
//        val mainScreenList = generateDummyList(500)
        recyclerView.setHasFixedSize(true)

//        recyclerView.adapter = view?.let { MainScreenItemAdapter(mainScreenList, it.context) }

//        val handler = Handler()
//        handler.postDelayed({view?.let { Navigation.findNavController(it).navigate(R.id.action_navigation_home_to_fragment_splash_screen) }},3000)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

//    fun LoadRecyclerView() {
//
//        // Specifying the url for the request
//        val url = "https://opentable.herokuapp.com/api/restaurants?city=Chicago"
//        // Setting up a request variable for the specified url
//        val request = Request.Builder().url(url).build()
//        // Creating a new OkHttpClient
//        val client = OkHttpClient()
//        client.newCall(request).enqueue(object : Callback {
//
//
//            override fun onResponse(call: Call, response: Response) {
//                val body = response.body?.string()
////                Log.e("Success", "Successful OkHttp request: $body")
//
//                // Setting up a gson variable
//                val gson = GsonBuilder().create()
//
//                val mainScreenList = gson.fromJson(body, MainScreenItemList::class.java)
////                Log.e("Success", "Successful Gson operation: ${mainScreenList.restaurants[0].name}")
//
////                mHandler.post {
////                    recyclerView.adapter = MainScreenItemAdapter(mainScreenList, view!!.context)
////                }
//            }
//
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("Failure", "Failed OkHttp request")
//            }
//        })
//    }

    private fun generateDummyList(size: Int): MainScreenItemList {
        val list = ArrayList<MainScreenItem>()
        for (i in 0 until size) {
            val item = MainScreenItem(0, "Restaurant nr. ${i}", "Address nr. ${i}","$i","https://www.opentable.com/img/restimages/107257.jpg")
            list += item
        }
        return MainScreenItemList(list)
    }
}