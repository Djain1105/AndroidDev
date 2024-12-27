package com.example.sharedpreferences

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var sPref = getPreferences(Context.MODE_PRIVATE)

        var color =  sPref.getInt("COLOR", Color.YELLOW)
        binding.root.setBackgroundColor(color)

        fun saveColor(color: Int) {
            var editor = sPref.edit()
            editor.putInt("COLOR", color)
            editor.apply()
        }

        binding.btnRed.setOnClickListener {
            binding.root.setBackgroundColor(Color.RED)
            saveColor(Color.RED)
        }
        binding.btnGreen.setOnClickListener {
            binding.root.setBackgroundColor(Color.GREEN)
            saveColor(Color.GREEN)
        }
        binding.btnBlue.setOnClickListener {
            binding.root.setBackgroundColor(Color.BLUE)
            saveColor(Color.BLUE)
        }

    }
}