package com.example.learning226.CoRoutine


import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.learning226.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class CoRoutineImageExample : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private val imageUrl = "https://img.freepik.com/free-photo/fashionable-young-guy-dressed-t-shirt-denim-jacket-posing-studio-isolated-dark-background_613910-5814.jpg?semt=ais_hybrid"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_co_routine_image_example)

        imageView = findViewById(R.id.coroutineImage)

        lifecycleScope.launch (Dispatchers.IO){
            val imageData = fetchImage(imageUrl)
            if(imageData != null){
                val bitmap = BitmapFactory.decodeByteArray(imageData,0,imageData.size)
                withContext(Dispatchers.Main){
                    imageView.setImageBitmap(bitmap)
                }
            }
        }
    }
    private suspend fun fetchImage(url:String):ByteArray?{
        return withContext(Dispatchers.IO){
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                response.body?.bytes()
            }
            else{
                null
            }

        }
    }
}