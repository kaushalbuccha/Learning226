package com.example.learning226.CoRoutine

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R

class CoRoutineExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_co_routine_example)

        val imageFetchButton = findViewById<Button>(R.id.buttonImageFetch)
        val videoFetchButton = findViewById<Button>(R.id.buttonVideoFetch)
        val pdfFetchButton = findViewById<Button>(R.id.buttonPDFFetch)
        val audioFetchButton = findViewById<Button>(R.id.buttonAudioFetch)
        val textFetchButton = findViewById<Button>(R.id.buttonTextFetch)

        imageFetchButton.setOnClickListener {
            val intent = Intent(this, CoRoutineImageExample::class.java)
            startActivity(intent)
        }

        videoFetchButton.setOnClickListener {
            val intent = Intent(this, CoRoutineVideoExample::class.java)
            startActivity(intent)
        }
        pdfFetchButton.setOnClickListener{
            val intent = Intent(this, CoRoutinePDFExample::class.java)
            startActivity(intent)
        }
        audioFetchButton.setOnClickListener{
            val intent = Intent(this, CoRoutineAudioExample::class.java)
            startActivity(intent)
        }
        textFetchButton.setOnClickListener{
            val intent = Intent(this, CoRoutineTextExample::class.java)
            startActivity(intent)
        }
    }
}