package com.example.dynamicfragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dynamicfragments.databinding.ActivityMainBinding

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

        binding.btnFruits.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, FruitsFragment()) // .add(R.id.flFragment, FruitsFragment()) -- don't use this because it will stack fragments on top
                .commit()
        }

        binding.btnVegetables.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, VegetablesFragment())
                .commit()
        }
    }
}