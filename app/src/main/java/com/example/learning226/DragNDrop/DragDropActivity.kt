package com.example.learning226.DragNDrop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R

class DragDropActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DragDropAdapter
    private lateinit var itemList: ArrayList<String>
    private lateinit var imageList: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_drop)

        recyclerView = findViewById(R.id.dragDropRecyclerView)
        itemList = ArrayList()
        imageList = ArrayList()

        imageList.add(R.drawable.fb)
        imageList.add(R.drawable.instagram)
        imageList.add(R.drawable.skype)
        imageList.add(R.drawable.linkedin)
        imageList.add(R.drawable.snapchat)
        imageList.add(R.drawable.twitter)
        imageList.add(R.drawable.whatsapp)
        imageList.add(R.drawable.telegram)
        imageList.add(R.drawable.yt)

        itemList.add("Facebook")
        itemList.add("Instagram")
        itemList.add("Skype")
        itemList.add("LinkedIn")
        itemList.add("Snapchat")
        itemList.add("Twitter")
        itemList.add("Whatsapp")
        itemList.add("Telegram")
        itemList.add("Youtube")

        adapter = DragDropAdapter(this, itemList, imageList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            0 // 0 disables swipe
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                adapter.moveItem(fromPosition, toPosition)
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Do nothing for now
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}