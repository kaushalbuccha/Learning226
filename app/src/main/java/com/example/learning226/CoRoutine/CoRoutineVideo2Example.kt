package com.example.learning226.CoRoutine

import android.os.Bundle
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning226.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoRoutineVideo2Example : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_co_routine_video2_example)

        videoView = findViewById(R.id.videoViewCoRoutine)
        progressBar = findViewById(R.id.progressBarVideoCoRoutine)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        progressBar.visibility = ProgressBar.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
            val videoUrl = loadVideoUrl()

            withContext(Dispatchers.Main){
                playVideo(videoUrl)
            }
        }
    }

    private suspend fun loadVideoUrl():String{
        delay(1000)
        return "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
    }
    private suspend fun playVideo(videoUrl:String){
        videoView.setVideoPath(videoUrl)
        videoView.start()
    }
}