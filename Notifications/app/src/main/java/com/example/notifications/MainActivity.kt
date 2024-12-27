package com.example.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notifications.databinding.ActivityMainBinding

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

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // to check services greater than OREO
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "first",
                "default",
                NotificationManager.IMPORTANCE_DEFAULT)

            // for Heads Up Notifications
//                val channel = NotificationChannel(
//                    "first",
//                    "default",
//                    NotificationManager.IMPORTANCE_HIGH)
//                channel.apply {
//                    enableLights(true)
//                    enableVibration(true)
//                }

            nm.createNotificationChannel(channel)
//        }

        // For Heads Up Notifications
//            binding.button.setOnClickListener {
//                val headsUpNotification = NotificationCompat.Builder(this, "first")
//                    .setContentTitle("Button 1")
//                    .setContentText("This is a simple notification")
//                    .setSmallIcon(R.drawable.ic_launcher_foreground)
//                    .setPriority(NotificationCompat.PRIORITY_MAX) // here changed to Priority Max
//                    .setDefaults(Notification.DEFAULT_VIBRATE or Notification.DEFAULT_LIGHTS) // added defaults
//                    .build()
//
//                nm.notify(1, headsUpNotification) // use different notification id's otherwise they will get overwritten
//            }

        binding.button.setOnClickListener {
            val simpleNotification = NotificationCompat.Builder(this, "first")
                .setContentTitle("Button 1")
                .setContentText("This is a simple notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            nm.notify(1, simpleNotification) // use different notification id's otherwise they will get overwritten
        }

        binding.button2.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.com")

            // intent created action on the context of the app is normal intent, while,
            // pending intent is for the future,
            // that is you might not click the notification as soon as it is received, but a pending intent for that activity should be there
            val pi = PendingIntent.getActivity(this, 123, i, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            val clickableNotification = NotificationCompat.Builder(this, "first")
                .setContentTitle("Button 2")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setContentText("This is a simple notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            nm.notify(2,clickableNotification) // use different notification id's otherwise they will get overwritten
        }

        binding.button3.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.com")

            // intent created action on the context of the app is normal intent, while,
            // pending intent is for the future,
            // that is you might not click the notification as soon as it is received, but a pending intent for that activity should be there
            val pi = PendingIntent.getActivity(this, 123, i, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            val clickableNotification = NotificationCompat.Builder(this, "first")
                .setContentTitle("Button 3")
                .addAction(R.drawable.ic_launcher_foreground, "Click Me", pi)
                .setAutoCancel(true)
                .setContentText("This is a simple notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            nm.notify(3,clickableNotification) // use different notification id's otherwise they will get overwritten
        }

    }
}