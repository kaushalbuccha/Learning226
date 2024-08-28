package com.example.learning226.CoRoutine

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

class CoRoutineTextExample : AppCompatActivity() {
    private lateinit var fetchTextFileButton: Button
    private lateinit var textFileContentTextView: TextView
    private val textFileUrl = "https://example-files.online-convert.com/document/txt/example.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_co_routine_text_example)

        fetchTextFileButton = findViewById(R.id.fetchTextFileButton)
        textFileContentTextView = findViewById(R.id.textFileContentTextView)

        fetchTextFileButton.setOnClickListener {
            fetchAndDisplayTextFile()
        }
    }

    private fun fetchAndDisplayTextFile() {
        textFileContentTextView.text = "Status: Fetching..."

        lifecycleScope.launch(Dispatchers.IO) {
            val textFileContent = fetchTextFile(textFileUrl)
            withContext(Dispatchers.Main) {
                if (textFileContent != null) {
                    textFileContentTextView.text = textFileContent
                } else {
                    textFileContentTextView.text = "Failed to fetch text file"
                }
            }
        }
    }
    private suspend fun fetchTextFile(url: String): String? {
        return withContext(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    response.body?.string()
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