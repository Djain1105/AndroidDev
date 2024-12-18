package com.example.viewbinding

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.viewbinding.databinding.ActivityMainBinding

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

//        val textView = findViewById<TextView>(R.id.textView)
//        textView.text = "hello divyansh"

        binding.textView.apply {
            text = "Hi Dj bro"
            setTextColor(getColor(R.color.blue))
            textSize = 30f
        }

//        with(binding.textView) {
//            text = "Hi Dj bro"
//            setTextColor(getColor(R.color.blue))
//            textSize = 30f
//        }

        binding.button.isEnabled = false
        var name: String? = null
        binding.editText.addTextChangedListener {
            name = it.toString()
            Log.i("ViewBinding", it.toString())
            binding.button.isEnabled = it.toString().length in 6..19
        }

        binding.button.setOnClickListener {
            Toast.makeText(it.context,"Typed name is $name", Toast.LENGTH_LONG).show()
        }

    }

//    fun showToast(view: View) {
//        Toast.makeText(view.context,"Button Pressed", Toast.LENGTH_SHORT).show()
//    }
}