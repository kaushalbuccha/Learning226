package com.example.learning226.GridViewExample

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning226.R
import java.util.ArrayList

class CustomGridViewExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_grid_view_example)

        val gridView = findViewById<GridView>(R.id.gridView)

        val arrayListImage = ArrayList<Int>();

        arrayListImage.add(R.drawable.fb)
        arrayListImage.add(R.drawable.instagram)
        arrayListImage.add(R.drawable.twitter)
        arrayListImage.add(R.drawable.whatsapp)
        arrayListImage.add(R.drawable.skype)
        arrayListImage.add(R.drawable.snapchat)
        arrayListImage.add(R.drawable.telegram)

        val name = arrayOf("Facebook","Instagram","Twitter","Whatsapp","Skype","Snapchat","Telegram")

        val customAdapter = CustomGridAdapter(this@CustomGridViewExample,arrayListImage,name)
        gridView.adapter = customAdapter

        gridView.setOnItemClickListener{adapterView, parent,position,l->
            Toast.makeText(this@CustomGridViewExample,name[position],Toast.LENGTH_SHORT).show()
        }
    }
}