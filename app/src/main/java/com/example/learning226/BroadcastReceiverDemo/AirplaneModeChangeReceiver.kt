package com.example.learning226.BroadcastReceiverDemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeChangeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneModeEnabled = intent?.getBooleanExtra("state",false)?:return
        if(isAirplaneModeEnabled){
            Toast.makeText(context,"Airplane Mode Enabled", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"Airplane Mode Disabled",Toast.LENGTH_SHORT).show()
        }
    }
}