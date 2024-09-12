package com.example.learning226.FloatingButtonExample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning226.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FloatingButtonDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_button_demo)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{ view->
            Snackbar.make(view,"Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action",null).show()
        }
    }
}