package com.example.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intents.databinding.ActivityMainBinding

const val KEY_1 = "Name"
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

        // Explicit Intent (Going to other activity in the same app)
        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(KEY_1, binding.editText.text.toString())
            startActivity(intent)
        }

        // Implicit Intent (Going to other application in the phone using data from this activity)
        binding.mailButton.setOnClickListener {
            val email = binding.editText.text.toString()
            val i = Intent()
            i.action = Intent.ACTION_SENDTO
            i.data = Uri.parse("mailto:$email")
            // i.putExtra(Intent.EXTRA_SUBJECT, "Implicit Intents")    ---- works for outlook
            startActivity(i)
        }

        binding.browseButton.setOnClickListener {
            val url = binding.editText.text.toString()
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("http://$url")
            startActivity(i)
        }

        binding.dialButton.setOnClickListener {
            val phone = binding.editText.text.toString()
            val i = Intent()
            i.action = Intent.ACTION_DIAL
            i.data = Uri.parse("tel:$phone")
            startActivity(i)
        }
    }
}