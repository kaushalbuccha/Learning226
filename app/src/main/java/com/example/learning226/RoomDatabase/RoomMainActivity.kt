package com.example.learning226.RoomDatabase

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R
import android.content.DialogInterface
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomMainActivity : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_main)

        database = Room.databaseBuilder(applicationContext,
            ContactDatabase::class.java, "contactDb").build()

        val display = findViewById<Button>(R.id.display)
        listView = findViewById(R.id.listView)

        listView.setOnItemLongClickListener { parent, view, position, id ->
            val view = parent.get(position)
            val id = view.findViewById<TextView>(R.id.idListItem).text.toString().toLong()
            val name = view.findViewById<TextView>(R.id.nameListItem).text.toString()
            val phone = view.findViewById<TextView>(R.id.phoneListItem).text.toString().toLong()

            var builder = AlertDialog.Builder(this)
            builder.setTitle("Edit")
            var linearLayout = LinearLayout(this)
            linearLayout.orientation = LinearLayout.VERTICAL

            val idView = EditText(this)
            idView.setText(id.toString())
            linearLayout.addView(idView)

            val nameView = EditText(this)
            nameView.text.clear()
            nameView.setText(name)
            linearLayout.addView(nameView)

            val phoneView = EditText(this)
            phoneView.text.clear()
            phoneView.setText(phone.toString())
            linearLayout.addView(phoneView)


//            Toast.makeText(this, "Updated $updatedName $updatedPhone", Toast.LENGTH_SHORT).show()

            builder.setView(linearLayout)

            builder.setPositiveButton("Edit", DialogInterface.OnClickListener{
                    dialog, which ->
                val updatedName = nameView.text.toString()
                val updatedPhone = phoneView.text.toString().toLong()
                GlobalScope.launch {
                    database.ContactDAO().update(Contact(id,updatedName,updatedPhone))
                }
                Toast.makeText(this, "Updated $updatedName $updatedPhone",
                    Toast.LENGTH_SHORT).show()

            })

            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener
            { dialog, which -> dialog.cancel() })

            builder.show()

            return@setOnItemLongClickListener true
        }

        display.setOnClickListener(){
            getData(it)
        }

        var btn = findViewById<Button>(R.id.addBtn)
        btn.setOnClickListener(){
            var id = findViewById<EditText>(R.id.idIn)
            var name = findViewById<EditText>(R.id.nameIn)
            var phone = findViewById<EditText>(R.id.phoneId)

            GlobalScope.launch {
                database.ContactDAO().insert(Contact(id.text.toString().toLong(),name.text.toString(),phone.text.toString().toLong()))
            }
        }
    }
    fun getData(view:View) {
        database.ContactDAO().getContact().observe(this){
            val adapter = ContactAdapter(this, R.layout.contact_list,it)
            listView.adapter = adapter
        }
    }
}