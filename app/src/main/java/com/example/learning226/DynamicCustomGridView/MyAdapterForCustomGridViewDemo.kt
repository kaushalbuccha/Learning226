package com.example.learning226.DynamicCustomGridView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.learning226.DyanmicCustomListView.ModelDemo
import com.example.learning226.R

class MyAdapterForCustomGridViewDemo(var mCtx: Context, var resources:Int, var items:List<GridModelDemo>):
    ArrayAdapter<GridModelDemo>(mCtx, resources,items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources,null)

        val imageView: ImageView = view.findViewById(R.id.dynamicGridimageView)
        val titleTextView: TextView = view.findViewById(R.id.dynamicGridTextView)

        val mItem: GridModelDemo = items[position]
        imageView.setImageDrawable(mCtx.resources.getDrawable(mItem.img))
        titleTextView.text = mItem.title
        return view
    }
}