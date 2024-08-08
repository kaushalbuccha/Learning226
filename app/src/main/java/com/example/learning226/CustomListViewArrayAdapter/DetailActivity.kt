package com.example.learning226.CustomListViewArrayAdapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning226.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView: ImageView = findViewById(R.id.imageView)
        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val button: Button = findViewById(R.id.button)

        val imageResId = intent.getIntExtra("imageResId", 0)
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val url = intent.getStringExtra("url")

        imageView.setImageResource(imageResId)
        nameTextView.text = name
        descriptionTextView.text = description

        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}