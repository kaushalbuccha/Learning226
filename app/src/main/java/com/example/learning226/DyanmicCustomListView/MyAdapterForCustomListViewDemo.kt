package com.example.learning226.DyanmicCustomListView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.learning226.R

class MyAdapterForCustomListViewDemo(var mCtx: Context, var resources:Int, var items:List<ModelDemo>):
    ArrayAdapter<ModelDemo>(mCtx, resources,items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources,null)

        val imageView: ImageView = view.findViewById(R.id.dynamicImage)
        val titleTextView: TextView = view.findViewById(R.id.dnmTextView1)
        val descriptionTextView: TextView = view.findViewById(R.id.dnmTextView2)

        val mItem: ModelDemo = items[position]
        imageView.setImageDrawable(mCtx.resources.getDrawable(mItem.img))
        titleTextView.text = mItem.title
        descriptionTextView.text = mItem.subTitle
        return view
    }
}