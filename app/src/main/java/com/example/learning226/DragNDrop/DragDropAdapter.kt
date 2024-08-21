package com.example.learning226.DragNDrop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R
import java.util.*

class DragDropAdapter(
    private val context: Context,
    private val itemList: ArrayList<String>,
    private val imageList: ArrayList<Int>
) : RecyclerView.Adapter<DragDropAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.drag_drop_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemText.text = itemList[position]
        holder.itemImage.setImageResource(imageList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(itemList, i, i + 1)
                Collections.swap(imageList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(itemList, i, i - 1)
                Collections.swap(imageList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.dragDropTextView)
        val itemImage: ImageView = itemView.findViewById(R.id.dragDropImg)
    }
}