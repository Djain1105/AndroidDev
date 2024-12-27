package com.example.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.*
import android.widget.Toast.makeText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.broadcastreceivers.databinding.ActivityMainBinding

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

        val psr = PowerStateReceiver()
        val iFilter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }

        registerReceiver(psr, iFilter)
    }

    inner class PowerStateReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                if(intent.action == Intent.ACTION_POWER_CONNECTED) {
                    Toast.makeText(this@MainActivity, "Power Connected", Toast.LENGTH_SHORT).show()
                }
                if(intent.action == Intent.ACTION_POWER_DISCONNECTED) {
                    Toast.makeText(this@MainActivity, "Power Disconnected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

// Complete Code of Main Activity is for Dynamic Broadcast Receiver
// Static Broadcast Receiver file is created separately in the same folder of Main Activity (LocaleChangedReceiver)