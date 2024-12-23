package com.example.asynctasks

import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.asynctasks.databinding.ActivityMainBinding

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

//        val TAG = "Async"
        binding.lvItems.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            arrayOf("Apple",
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
                "Grape")
        )

        binding.button.setOnClickListener{
//            var ctask = countTask()
            waitNsec(3)
            binding.main.setBackgroundColor(Color.GREEN)
        }

//        class countTask: AsyncTask<Int, Int, Void>() {
//            override fun doInBackground(vararg params: Int?): Void? {
//                Log.d(TAG,"started:")
//                var n: Int? = params[0]
//                waitNsec(n)
//                Log.d(TAG,"Stopped: ")
//                return null
//            }
//        }
        
    }
    
    private fun waitNsec(i: Int) {
        for(x in 0..i){
            wait1sec()
        }
    }
    private fun wait1sec() {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() < startTime + 1000){
            continue
        }
    }
}