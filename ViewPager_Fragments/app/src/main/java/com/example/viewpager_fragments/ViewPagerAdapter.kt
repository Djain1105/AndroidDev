package com.example.viewpager_fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    val list = arrayListOf<Fragment>()

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    fun add(fragment: Fragment) {
        list.add(fragment)
    }
}