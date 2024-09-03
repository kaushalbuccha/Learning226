package com.example.learning226.ForegroundServices

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.learning226.R


class ForegroundServicesHelper: Service() {
    private val channelID = "ForegroundService Kotlin";
    override fun onBind(p0: Intent?): IBinder? {
         TODO("NOT yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this,ForegroundServicesExample::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this,channelID)
            .setContentTitle("Foreground Service Kotlin Example")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        startForeground(1,notification,FOREGROUND_SERVICE_TYPE_LOCATION)
        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        val serviceChannel = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel(channelID,"Foreground Service Channel",NotificationManager.IMPORTANCE_DEFAULT)
        }
        else{
            TODO("VERSION.SDK_INT < 0")
        }
        val manager = getSystemService(NotificationManager::class.java)
        manager!!.createNotificationChannel(serviceChannel)
    }
}