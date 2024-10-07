package com.example.learning226.SQLite

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.learning226.R

class UserAdapter(private val context: Context, private val users: List<UserDataModel>) : BaseAdapter() {

    override fun getCount(): Int {
        return users.size
    }

    override fun getItem(position: Int): Any {
        return users[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false)

        val nameTextView = view.findViewById<TextView>(R.id.user_name)
        val ageTextView = view.findViewById<TextView>(R.id.user_age)
        val imageView = view.findViewById<ImageView>(R.id.user_image)

        val user = users[position]

        nameTextView.text = user.name
        ageTextView.text = user.age

        // Convert byte array to Bitmap and set in ImageView
        val bitmap = BitmapFactory.decodeByteArray(user.image, 0, user.image.size)
        imageView.setImageBitmap(bitmap)

        return view
    }
}
