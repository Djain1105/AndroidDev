package com.example.listviews

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listviews.databinding.ActivityMainBinding

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

        binding.lvFruits.adapter = ArrayAdapter<String>(
            this,
            R.layout.list_item_fruit,
            R.id.tvFruitName,
            arrayOf(
                "Apple",
                "Banana",
                "Orange",
                "Watermelon",
                "Pear",
                "Kiwi",
                "Papaya",
                "Strawberry",
                "Cherry",
                "Mango",
                "Pineapple",
                "Grape",
                "Apple",
                "Banana",
                "Orange",
                "Watermelon",
                "Pear",
                "Kiwi",
                "Papaya",
                "Strawberry",
                "Cherry",
                "Mango",
                "Pineapple",
                "Grape",
            )
        )

        binding.lvFruits.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Clicked: ${position+1} position having fruit ${view?.findViewById<TextView>(R.id.tvFruitName)?.text}", Toast.LENGTH_LONG).show()
        }
    }
}