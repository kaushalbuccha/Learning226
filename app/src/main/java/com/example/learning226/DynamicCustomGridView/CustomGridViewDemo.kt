package com.example.learning226.DynamicCustomGridView

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning226.DyanmicCustomListView.ModelDemo
import com.example.learning226.R
import kotlin.random.Random

class CustomGridViewDemo : AppCompatActivity() {
    lateinit var dynamicGridEt1: EditText
    lateinit var dynamicGridEt2 : EditText
    lateinit var dynamicGridbtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_grid_view_demo)

        var gridView = findViewById<GridView>(R.id.dynamicGridView)
        var list = mutableListOf<GridModelDemo>()

        dynamicGridEt1 = findViewById(R.id.dynamicGridEt1)
        dynamicGridEt2 = findViewById(R.id.dynamicGridEt2)
        dynamicGridbtn = findViewById(R.id.dynamicGridBtn)

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

        val myAdapter = MyAdapterForCustomGridViewDemo(this,R.layout.mycustomgridlookfile,list)
        gridView.adapter = myAdapter

        dynamicGridbtn.setOnClickListener{
            val randomImage = imgList[Random.nextInt(imgList.size)]
            list.add(GridModelDemo(dynamicGridEt1.text.toString(),dynamicGridEt2.text.toString(),randomImage))
            myAdapter.notifyDataSetChanged()
        }

        gridView.setOnItemClickListener{ parent: AdapterView<*>, view: View, position:Int, id:Long ->
            list.removeAt(position)
            myAdapter.notifyDataSetChanged()
        }
    }
}