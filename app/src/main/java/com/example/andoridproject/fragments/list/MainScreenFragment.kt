package com.example.andoridproject.fragments.list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andoridproject.R
import com.example.andoridproject.data.favorite.FavoriteViewModel
import com.example.andoridproject.data.favorite.MainScreenItem
import kotlinx.android.synthetic.main.fragment_main_screen.*


class MainScreenFragment : Fragment() {

    private val mHandler: Handler = Handler(Looper.getMainLooper())
    private lateinit var mFavoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // LoadRecyclerView() -- API error

        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

//        mFavoriteViewModel.readFavoriteData.observe(viewLifecycleOwner,{ MainScreenItem ->
//            recyclerView.adapter = view?.let { MainScreenItemAdapter(MainScreenItem, it.context) }
//        })

        mFavoriteViewModel.readFavoriteData.observe(viewLifecycleOwner, { list ->
            val mergedList = mergeLists(list,generateDummyList(500))

            recyclerView.layoutManager = LinearLayoutManager(this.context)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = view?.let { MainScreenItemAdapter(mergedList, it.context) }
        })

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

    // Merges the two favorite list from the database with the list that comes from the api, prioritizing the favorite list
    private fun mergeLists(favoriteList: List<MainScreenItem>, apiList: List<MainScreenItem>): List<MainScreenItem>{
        val mergedList = ArrayList<MainScreenItem>()
        for (i in favoriteList){
            mergedList += i
        }
        for (i in apiList){
            if(!existsInFavoriteList(i, favoriteList)){
                mergedList += i
            }
        }
        Log.e("without duplicant detection","$mergedList")
        return mergedList
    }

    private fun existsInFavoriteList(item: MainScreenItem, list: List<MainScreenItem>): Boolean{
        for(i in list){
            if (i.name == item.name){
                return true
            }
        }
        return false
    }

    // Generates dummy data
    private fun generateDummyList(size: Int): List<MainScreenItem> {
        val list = ArrayList<MainScreenItem>()
        for (i in 0 until size) {
            val item = MainScreenItem(0, "Restaurant nr. ${i}", "Address nr. ${i}","$i","https://www.opentable.com/img/restimages/107257.jpg", 0)
            list += item
        }
        return list
    }
}