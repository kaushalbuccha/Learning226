package com.example.learning226.CustomListViewArrayAdapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning226.MainActivity
import com.example.learning226.R
import com.example.learning226.SearchActivity.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomListViewArrayAdapterExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list_view_array_adapter_example)

        var listView = findViewById<ListView>(R.id.listview)
        var list = mutableListOf<Model>()

        list.add(Model("Facebook", "A social network for connecting with people, sharing updates, photos, and videos, and joining groups and events.", R.drawable.fb, "https://www.facebook.com"))
        list.add(Model("Skype", "Video and voice calling platform for personal and professional communication.", R.drawable.skype, "https://www.skype.com"))
        list.add(Model("Twitter", " Post and follow short updates, news, and trends with hashtags.", R.drawable.twitter, "https://www.twitter.com"))
        list.add(Model("Whatsapp", "Messaging app for text, voice, and video communication with end-to-end encryption.", R.drawable.whatsapp, "https://www.whatsapp.com"))
        list.add(Model("Youtube", " Platform for uploading, watching, and sharing videos on various topics.", R.drawable.yt, "https://www.youtube.com"))
        list.add(Model("LinkedIn", "Professional networking site for career connections, job searching, and industry news.", R.drawable.linkedin, "https://www.linkedin.com"))
        list.add(Model("Telegram", "Messaging app known for its speed, security, and large group features.", R.drawable.telegram, "https://telegram.org"))
        list.add(Model("Instagram", "Photo and video sharing app with stories, posts, and reels for social engagement.", R.drawable.instagram, "https://www.instagram.com"))
        list.add(Model("Snapchat", "Multimedia messaging app with temporary photos and videos, and interactive filters.", R.drawable.snapchat, "https://www.snapchat.com"))

        listView.adapter = MyAdapter(this, R.layout.listviewrow, list)

        listView.setOnItemClickListener{ parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val selectedItem = list[position]
            val intent = Intent(this,DetailActivity::class.java).apply {
                putExtra("imageResId", selectedItem.img)
                putExtra("name", selectedItem.title)
                putExtra("description", selectedItem.description)
                putExtra("url", selectedItem.url)
            }
            startActivity(intent)
        }


    }
}

//class CustomListViewArrayAdapterExapmle : AppCompatActivity(){
//    override fun onCreate(savedInstanceState: Bundle?){
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.xmlactiviy)
//        var listView = findViewById<ListView>(R.id.listview)
//        var list = mutableListOf<Model>()
//
//        list.add(Model("Facebook", "desc", R.drawable.fb))
//        list.add(Model("SKype", "desc", R.drawable.fb))
//        list.add(Model("Twitter", "desc", R.drawable.fb))
//        list.add(Model("Whatp", "desc", R.drawable.fb))
//        list.add(Model("Yt", "desc", R.drawable.fb))
//
//        listView.adapter = MyAdapter(this, R.layout.listviewrow, list)
//
//        listVew.setOnItemClickListener{parent: AdapterView<*>, view: VIew, position: Int, id: Long ->
//            if(position == 0){
//                Toast.makeText(this or applicationcontext, "Facebok", Toast.Length_Long)
//            }
//        }
//    }
//}