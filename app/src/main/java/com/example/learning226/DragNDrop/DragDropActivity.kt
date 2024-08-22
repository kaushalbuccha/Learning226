package com.example.learning226.DragNDrop

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R
import com.google.android.material.snackbar.Snackbar

class DragDropActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DragDropAdapter
    private lateinit var itemList: ArrayList<String>
    private lateinit var imageList: ArrayList<Int>

    private var recentlyDeletedItem: String? = null
    private var recentlyDeletedItemImage: Int? = null
    private var recentlyDeletedItemPosition: Int = -1

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
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
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
                val position = viewHolder.adapterPosition
                val item = itemList[position]
                Log.d("SwipeTest", "Item swiped in direction: $direction")
                if (direction == ItemTouchHelper.LEFT) {

                    recentlyDeletedItem = itemList[position]
                    recentlyDeletedItemImage = imageList[position]
                    recentlyDeletedItemPosition = position

                    itemList.removeAt(position)
                    imageList.removeAt(position)
                    adapter.notifyItemRemoved(position)

                    showUndoSnackbar()
                }
                else if (direction == ItemTouchHelper.RIGHT) {
                    Toast.makeText(this@DragDropActivity, "Swiped: $item", Toast.LENGTH_SHORT).show()
                    adapter.notifyItemChanged(position)
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    private fun showUndoSnackbar() {
        val view = findViewById<View>(android.R.id.content)
        val snackbar = Snackbar.make(view, "$recentlyDeletedItem deleted", Snackbar.LENGTH_LONG)
        snackbar.setAction("UNDO") {
            itemList.add(recentlyDeletedItemPosition, recentlyDeletedItem!!)
            imageList.add(recentlyDeletedItemPosition, recentlyDeletedItemImage!!)
            adapter.notifyItemInserted(recentlyDeletedItemPosition)
        }
        snackbar.show()
    }
}