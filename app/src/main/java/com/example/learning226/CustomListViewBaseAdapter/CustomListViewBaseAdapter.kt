package com.example.learning226.CustomListViewBaseAdapter

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning226.MainActivity
import com.example.learning226.R
import com.example.learning226.SearchActivity.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomListViewBaseAdapter : AppCompatActivity() {
    lateinit var listView: ListView
    var arrayList:ArrayList<MyData> = ArrayList()
    var adapter:MyBaseAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list_view_base_adapter)

        title = "ListView BaseAdapter"
        listView = findViewById(R.id.listView)
        arrayList.add(MyData(1,"Ajay","9876543210"))
        arrayList.add(MyData(2,"Anand","8787576768"))
        arrayList.add(MyData(3,"Akash","65757657657"))
        adapter = MyBaseAdapter(this,arrayList)
        listView.adapter = adapter

    }
}