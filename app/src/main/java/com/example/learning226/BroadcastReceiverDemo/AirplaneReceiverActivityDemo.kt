package com.example.learning226.BroadcastReceiverDemo

import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R

class AirplaneReceiverActivityDemo : AppCompatActivity() {
    lateinit var receiver:ModeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_airplane_receiver_demo)

        receiver = ModeChangeReceiver()

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver ,it)
        }
        IntentFilter(AudioManager.RINGER_MODE_CHANGED_ACTION).also {
            registerReceiver(receiver, it)
        }
        IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).also {
            registerReceiver(receiver, it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}

