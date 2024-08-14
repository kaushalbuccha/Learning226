package com.example.learning226.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R

class AdapterRecycler(private val context:Context,private val arrayList: ArrayList<RecyclerModel>):
    RecyclerView.Adapter<AdapterRecycler.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.custom_recycler_list,parent,
            false)
        return MyHolder(view,context)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
       val item = arrayList[position]
        holder.versionName.text = item.versionName
        holder.version.text = item.version
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyHolder(view: View,context: Context):RecyclerView.ViewHolder(view){
        val versionName:TextView = view.findViewById(R.id.version_name)
        val version: TextView = view.findViewById(R.id.version)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Communicate with the activity to delete the item
                    (context as? RecyclerViewExample)?.deleteItem(position)
                }
            }
        }
    }
}