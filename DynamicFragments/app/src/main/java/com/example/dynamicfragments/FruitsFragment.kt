package com.example.dynamicfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class FruitsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_fruits, container, false)

        fragmentView.findViewById<ListView>(R.id.lvFruits).adapter = ArrayAdapter<String> (
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            arrayOf("Apple", "Mango", "Banana", "Grapes", "Orange")
        )
        return fragmentView
    }

}