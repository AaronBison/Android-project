package com.example.andoridproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andoridproject.R
import kotlinx.android.synthetic.main.fragment_main_screen.*

class MainScreenFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mainScreenList = generateDummyList(500)
        recyclerView.adapter = MainScreenItemAdapter(mainScreenList)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_screen, container, false)

        return view
    }

    private fun generateDummyList(size: Int): List<MainScreenItem> {
        val list = ArrayList<MainScreenItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.restaurant_placeholder
                1 -> R.drawable.restaurant_placeholder
                else -> R.drawable.restaurant_placeholder
            }
            val item = MainScreenItem(drawable, "Item $i", "Line 2", "£ ${i*13+47}")
            list += item
        }
        return list
    }

}