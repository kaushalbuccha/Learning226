package com.example.learning226.GridViewExample

import android.app.Activity
import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.learning226.R
import kotlinx.coroutines.NonDisposableHandle.parent

class CustomGridAdapter(context:Context,arrayListImage:ArrayList<Int>,name:Array<String>):BaseAdapter() {

    var context = context
    var arrayListImage = arrayListImage
    var name = name

    override fun getCount(): Int {
        return arrayListImage.size
    }

    override fun getItem(p0: Int): Any {
        return arrayListImage.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myView = convertView
        var holder : viewHolder

        if(myView == null){
            val mInflater = (context as Activity).layoutInflater
            myView = mInflater.inflate(R.layout.grid_item,parent,false)
            holder = viewHolder()

            holder.mImageView = myView!!.findViewById(R.id.imageView) as ImageView
            holder.mTextView = myView!!.findViewById(R.id.textView) as TextView
            myView.tag = holder
        }
        else{
            holder = myView.tag as viewHolder
        }

        holder.mImageView!!.setImageResource(arrayListImage.get(position))
        holder.mTextView!!.setText(name.get(position))
        return myView
    }
    class viewHolder{
        var mImageView:ImageView? = null
        var mTextView: TextView? = null
    }

}