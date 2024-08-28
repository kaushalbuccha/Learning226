package com.example.learning226.CoRoutine

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.learning226.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class CoRoutineVideoExample : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private val videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_co_routine_video_example)

        videoView = findViewById(R.id.videoView)
        val playButton = findViewById<Button>(R.id.buttonPlayVideo)

        playButton.setOnClickListener {
            lifecycleScope.launch {
                val videoUri = fetchVideoUrl(videoUrl)
                withContext(Dispatchers.Main) {
                    if (videoUri != null) {
                        videoView.setVideoURI(videoUri)
                        videoView.start()
                    } else {
                        // Handle error (e.g., show a Toast)
                    }
                }
            }
        }
    }
    private suspend fun fetchVideoUrl(url: String): Uri? {
        return withContext(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    // Returning the Uri directly for streaming
                    Uri.parse(url)
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}