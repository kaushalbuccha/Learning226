package com.example.learning226.DyanmicCustomListView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning226.R
import kotlin.random.Random

class CustomListViewDemo : AppCompatActivity() {
    lateinit var et1:EditText
    lateinit var et2 :EditText
    lateinit var add:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list_view_demo)

        var listView = findViewById<ListView>(R.id.dyanmicListView)
        var list = mutableListOf<ModelDemo>()
        et1 = findViewById(R.id.dynamicet1)
        et2 = findViewById(R.id.dynamicet2)
        add = findViewById(R.id.dynamicBtn)

        var imgList = ArrayList<Int>();
        imgList.add(R.drawable.fb)
        imgList.add(R.drawable.instagram)
        imgList.add(R.drawable.linkedin)
        imgList.add(R.drawable.skype)
        imgList.add(R.drawable.snapchat)
        imgList.add(R.drawable.telegram)
        imgList.add(R.drawable.twitter)
        imgList.add(R.drawable.whatsapp)
        imgList.add(R.drawable.yt)
        var ad = MyAdapterForCustomListViewDemo(this,R.layout.mycustomlookfile,list)
        listView.adapter = ad
        add.setOnClickListener{
            val randomImage = imgList[Random.nextInt(imgList.size)]
            list.add(ModelDemo(et1.text.toString(),et2.text.toString(),randomImage))
            ad.notifyDataSetChanged()
        }
        listView.setOnItemClickListener{parent: AdapterView<*>,view:View,position:Int,id:Long ->
            if(position == 0)
                Toast.makeText(applicationContext,"Facebook",Toast.LENGTH_SHORT).show()
            list.removeAt(position)
            ad.notifyDataSetChanged()
        }

    }
}