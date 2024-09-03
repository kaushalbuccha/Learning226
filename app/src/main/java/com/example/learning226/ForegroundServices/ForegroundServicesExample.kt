package com.example.learning226.ForegroundServices

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.learning226.R

class ForegroundServicesExample : AppCompatActivity() {
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_services_example)

        val startButton = findViewById<Button>(R.id.btn1ForegroundServices)
        val stopButtom = findViewById<Button>(R.id.btn2ForegroundServices)

        startButton.setOnClickListener {
            if(hasPermissions()){
                startForegroundService()
            }
            else{
                requestPermissions()
            }
        }
        stopButtom.setOnClickListener {
            stopForegroungService()
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun hasPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(this,android.Manifest.permission.FOREGROUND_SERVICE) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun requestPermissions(){
        ActivityCompat.requestPermissions(this,
            arrayOf(
                android.Manifest.permission.FOREGROUND_SERVICE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun startForegroundService(){
        val startIntent = Intent(this,ForegroundServicesHelper::class.java)
        startIntent.putExtra("inputExtra","Foreground Service is running")
        ContextCompat.startForegroundService(this,startIntent)
    }

    private fun stopForegroungService(){
        val stopIntent = Intent(this,ForegroundServicesHelper::class.java)
        stopService(stopIntent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }){
                startForegroundService()
            }
            else{

            }
        }
    }
}