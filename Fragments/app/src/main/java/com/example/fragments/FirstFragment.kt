package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val name = arguments?.getString("Key")?: "Default" // using to receive arguments from main activity
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        view.findViewById<TextView>(R.id.tvFirst).text = name
        return view
    }
}