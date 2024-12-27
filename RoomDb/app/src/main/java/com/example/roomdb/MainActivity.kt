package com.example.roomdb

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.roomdb.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// made changes in the gradle file to add the room in dependencies


class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding

    val db by lazy {
        Room.databaseBuilder(this,
            AppDatabase::class.java,
            "User.db")
//            .allowMainThreadQueries() //allowing to access the db on main thread
            .fallbackToDestructiveMigration() // destructing the old db and creating new everytime accessed
            .build()
    }

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

        binding.button.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) { db.userDao().insert(User("Divyansh Jain", "8107118899", "Ajmer", 22)) }
        }
        binding.button2.setOnClickListener {
//            runBlocking { // not to be used with live data
//                val list = GlobalScope.async(Dispatchers.IO){db.userDao().getAllUser()}
            db.userDao().getAllUser().observe(this, Observer {list ->   //used to work with live data, also in this data is automatically updated in the text, and you no need to fetch the data, so we can also skip the button on click listener part
                if(list.isNotEmpty()) {     // list.await().isNotEmpty()
                    with(list[list.size-1]) { // list.await()[0]
                        binding.textView.text = name+id
                        binding.textView2.text = number
                        binding.textView3.text = address
                        binding.textView4.text = age.toString()
                    }
                }
            })
        }

    }
}