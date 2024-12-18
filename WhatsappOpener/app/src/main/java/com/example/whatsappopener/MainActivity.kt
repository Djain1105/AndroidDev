package com.example.whatsappopener

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.whatsappopener.databinding.ActivityMainBinding

// here we have also changed the theme... check in android manifest
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

        var number: String = "0"
        if(intent.action == Intent.ACTION_PROCESS_TEXT){
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }

        if(number.isDigitsOnly()){
            startWhatsapp(number)
        }else{
            Toast.makeText(this,"Please check the number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startWhatsapp(number: String) {
        val intent = Intent(Intent.ACTION_VIEW)
//        intent.setPackage("com.whatsapp")

        val data: String = if(number[0]=='+'){
            number.substring(1)
        }
        else if(number.length==10){
            "91" + number
        }
        else{
            number
        }
        intent.data = Uri.parse("https://wa.me/$data")
        if(packageManager.resolveActivity(intent,0) != null){
            startActivity(intent)
        }else{
            Toast.makeText(this,"Please install Whatsapp", Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}