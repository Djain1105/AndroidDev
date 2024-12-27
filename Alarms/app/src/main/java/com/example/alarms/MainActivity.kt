package com.example.alarms

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alarms.databinding.ActivityMainBinding

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

        val am = getSystemService(ALARM_SERVICE) as AlarmManager

        binding.btnTask.setOnClickListener {

            // Scheduling a task that will run after 5 minutes from now and start my app
            val i = Intent(this,MainActivity2::class.java)

            val pi = PendingIntent.getActivity(this,123,i,
                PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE) // flag_one_shot will make the intent to appear utmost once

            am.set(AlarmManager.ELAPSED_REALTIME,SystemClock.elapsedRealtime()+1*10*1000,pi)

            // change the flag of Pending Intent to Flag_Update_Current for repeating alarms
//            am.setRepeating(AlarmManager.ELAPSED_REALTIME,
//                SystemClock.elapsedRealtime()+1*10*1000, // time after which the alarm will firstly get triggered
//                1*10*1000, // time interval after which alarm will be triggered after first instance
//                pi)
        }

    }
}