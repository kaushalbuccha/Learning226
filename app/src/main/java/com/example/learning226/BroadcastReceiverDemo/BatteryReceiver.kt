package com.example.learning226.BroadcastReceiverDemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.TextView
import android.widget.Toast

class BatteryReceiver constructor(var tv: TextView, val context:Context):BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val perc = intent.getIntExtra("level",0)
        if(perc != 0){
            tv.text = "$perc%"
        }

        val batteryStaus: Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS,0)
        val isCharging:Boolean = batteryStaus == BatteryManager.BATTERY_STATUS_CHARGING || batteryStaus == BatteryManager.BATTERY_STATUS_FULL

        if(isCharging)
            Toast.makeText(context,"Charger Connected",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,"Charger Disconnected",Toast.LENGTH_LONG).show()
    }
}