package com.example.filereadwrite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.filereadwrite.databinding.ActivityMainBinding
import java.io.File

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

        binding.btnWrite.setOnClickListener {
            val text = binding.editText.text.toString()

            val dataDir = ContextCompat.getDataDir(this)
            val myFile = File(dataDir, "file.txt")
            myFile.writeText(text)
        }
        binding.btnRead.setOnClickListener {
            val dataDir = ContextCompat.getDataDir(this)
            val myFile = File(dataDir, "file.txt")
            val text = myFile.readText()
            binding.textView.text = text
        }
    }
}