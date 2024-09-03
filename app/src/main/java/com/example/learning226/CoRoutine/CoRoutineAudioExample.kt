package com.example.learning226.CoRoutine

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
import java.io.File

class CoRoutineAudioExample : AppCompatActivity() {
    private lateinit var fetchAudioButton: Button
    private lateinit var audioStatusTextView: TextView
    private val audioUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3"
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_co_routine_audio_example)

        fetchAudioButton = findViewById(R.id.fetchAudioButton)
        audioStatusTextView = findViewById(R.id.audioStatusTextView)

        fetchAudioButton.setOnClickListener {
            fetchAndPlayAudio()
        }

    }

    private fun fetchAndPlayAudio() {
        audioStatusTextView.text = "Status: Fetching..."

        lifecycleScope.launch(Dispatchers.IO) {
            val audioData = fetchAudioData(audioUrl)
            if (audioData != null) {
                playAudio(audioData)
                withContext(Dispatchers.Main) {
                    audioStatusTextView.text = "Status: Playing"
                }
            } else {
                withContext(Dispatchers.Main) {
                    audioStatusTextView.text = "Status: Failed to fetch audio"
                }
            }
        }
    }

    private suspend fun fetchAudioData(url: String): ByteArray? {
        return withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                response.body?.bytes()
            } else {
                null
            }
        }
    }

    private fun playAudio(audioData: ByteArray) {
//        mediaPlayer = MediaPlayer().apply {
//            setDataSource(audioData.inputStream().fd)
//            prepare()
//            start()
//        }
        val tempFile = File.createTempFile("temp_audio", "mp3", cacheDir)
        tempFile.deleteOnExit()
        tempFile.outputStream().use { it.write(audioData) }

        // Play the audio from the temporary file
        mediaPlayer = MediaPlayer().apply {
            setDataSource(tempFile.absolutePath)
            prepare()
            start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}