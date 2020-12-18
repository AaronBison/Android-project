package com.example.andoridproject.fragments.list

import  android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andoridproject.R
import com.example.andoridproject.data.favorite.FavoriteViewModel
import com.example.andoridproject.data.favorite.MainScreenItem
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_main_screen.*
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class MainScreenFragment : Fragment() {

    private lateinit var mFavoriteViewModel: FavoriteViewModel
    private val mHandler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

         loadRecyclerView()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    // Creates okHttp client, receives the response and sends it to processing function
    private fun loadRecyclerView() {

        // Specifying the url for the request
        val url = "https://ratpark-api.imok.space/restaurants/"
        // Setting up a request variable for the specified url
        val request = Request.Builder().url(url).build()
        // Creating a new OkHttpClient
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                // Setting up a gson variable
                val gson = GsonBuilder().create()

                val mainScreenList = gson.fromJson(body, MainScreenItemList::class.java)
//                Log.e("Success", "Successful Gson operation: ${mainScreenList.restaurants[0].name}")

                mHandler.post {
                    mFavoriteViewModel = ViewModelProvider(this@MainScreenFragment).get(FavoriteViewModel::class.java)

                    mFavoriteViewModel.readFavoriteData.observe(viewLifecycleOwner, { favoriteList ->
                        val mergedList = mergeLists(favoriteList, mainScreenList.restaurants)
                        filterManager(mergedList)
                    })
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Failure", "Failed OkHttp request")
            }
        })
    }

    // Handles the spinner and sets the search field according to it
    private fun filterManager(mergedList: List<MainScreenItem>){

        val pricesList = arrayOf("Name", "Address", "Price")
            val arrayAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, pricesList)
            mainScreenSpinner.adapter = arrayAdapter

            mainScreenSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val searchBy = pricesList[position]
                    val searchedList = searchViewManager(mergedList, searchBy)

                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.setHasFixedSize(true)
                    recyclerView.adapter = view?.let { MainScreenItemAdapter(searchedList, it.context) }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(requireContext(), "Nothing is selected!", Toast.LENGTH_SHORT)
                        .show()
                }

            }
    }

    // Recreates a correct list based on the search text
    private fun searchViewManager(
        mergedList: List<MainScreenItem>,
        searchBy: String
    ): ArrayList<MainScreenItem> {

        val searchView = mainScreenItemSV
        val mergedDisplayList = ArrayList<MainScreenItem>(mergedList)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    mergedDisplayList.clear()
                    val search = newText.toLowerCase(Locale.getDefault())

                    mergedList.forEach {
                        when (searchBy.toLowerCase(Locale.getDefault())) {
                            "name" -> if (it.name.toLowerCase(Locale.getDefault())
                                    .contains(search)
                            ) {
                                mergedDisplayList.add(it)
                            }
                            "address" -> if (it.address.toLowerCase(Locale.getDefault())
                                    .contains(search)
                            ) {
                                mergedDisplayList.add(it)
                            }
                            "price" -> if (it.price.toLowerCase(Locale.getDefault())
                                    .contains(search)
                            ) {
                                mergedDisplayList.add(it)
                            }
                        }
                    }

                    // Notifies the recycler view to reload, because data has changed in the list
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    mergedDisplayList.clear()
                    mergedDisplayList.addAll(mergedList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return true
            }
        })

        return mergedDisplayList
    }


    // Merges the two favorite list from the database with the list that comes from the api, prioritizing the favorite list
    private fun mergeLists(
        favoriteList: List<MainScreenItem>,
        apiList: List<MainScreenItem>
    ): List<MainScreenItem> {
        val mergedList = ArrayList<MainScreenItem>()
        for (i in favoriteList) {
            mergedList += i
        }
        for (i in apiList) {
            if (!existsInFavoriteList(i, favoriteList)) {
                mergedList += i
            }
        }
        return mergedList
    }

    // Assistant function -- checks if an items name already exist in a given list
    private fun existsInFavoriteList(item: MainScreenItem, list: List<MainScreenItem>): Boolean {
        for (i in list) {
            if (i.name == item.name) {
                return true
            }
        }
        return false
    }
}