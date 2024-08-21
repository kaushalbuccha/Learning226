package com.example.learning226.RecyclerGridView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.learning226.R
import com.example.learning226.RecyclerView.AdapterRecycler
import com.example.learning226.RecyclerView.RecyclerModel

class AdapterRecyclerGrid(private val contextGrid: Context, private val arrayList: ArrayList<RecyclerGridModel>):
    RecyclerView.Adapter<AdapterRecyclerGrid.MyHolderGrid>() {
        var context:Context
        var arrayListGrid:ArrayList<RecyclerGridModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderGrid {
        val view: View = LayoutInflater.from(context).inflate(R.layout.custom_recycler_grid,parent,false)
        return MyHolderGrid(view)
    }

    override fun getItemCount(): Int {
        return arrayListGrid.size
    }

    override fun onBindViewHolder(holder: MyHolderGrid, position: Int) {
       holder.name.text = arrayListGrid[position].name
        holder.img.setImageResource(arrayListGrid[position].img)
        holder.itemView.setOnClickListener{
            Toast.makeText(context,""+arrayListGrid[position].name,Toast.LENGTH_SHORT).show()
        }
        holder.btn.setOnClickListener{
            arrayListGrid.removeAt(position)
            notifyDataSetChanged()
        }
    }
    class MyHolderGrid(view: View):RecyclerView.ViewHolder(view){
        var name:TextView
        var img:ImageView
        var btn:Button
        init {
            name = view.findViewById(R.id.recyclerGridName)
            img = view.findViewById(R.id.recyclergridImg)
            btn = view.findViewById(R.id.recylerGridBtn)
        }
    }
    init {
        this.context = contextGrid
        this.arrayListGrid = arrayList
    }

}