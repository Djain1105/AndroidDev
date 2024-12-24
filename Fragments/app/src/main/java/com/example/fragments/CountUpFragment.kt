package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 * Use the [CountUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var count = 0
        val fragmentView = inflater.inflate(R.layout.fragment_count_up, container, false)

        fragmentView.findViewById<Button>(R.id.btnAdd).setOnClickListener {
            count++
            fragmentView.findViewById<TextView>(R.id.tvCounter).text = count.toString()
        }
        return fragmentView
    }
}