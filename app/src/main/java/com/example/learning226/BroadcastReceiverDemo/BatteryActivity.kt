package com.example.learning226.BroadcastReceiverDemo

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R

class BatteryActivity : AppCompatActivity() {
    lateinit var tv: TextView
    lateinit var br: BatteryReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battery_receiver)

        tv = findViewById(R.id.tv)
        br = BatteryReceiver(tv,this)
        registerReceiver(br, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }
}