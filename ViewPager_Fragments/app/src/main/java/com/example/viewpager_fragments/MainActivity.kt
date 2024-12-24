package com.example.viewpager_fragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.viewpager_fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewPagerAdapter =  ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.add(FirstFragment())
        viewPagerAdapter.add(SecondFragment())
        viewPagerAdapter.add(FirstFragment())
        viewPagerAdapter.add(SecondFragment())
        binding.viewPager.adapter = viewPagerAdapter

        // to apply transformation to the View Pager
        binding.viewPager.setPageTransformer(true,ZoomOutPageTransformer())
//        binding.viewPager.setPageTransformer(true,DepthPageTransformer())

    }
}