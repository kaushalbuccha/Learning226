package com.example.learning226.SQLite

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.learning226.R

class DisplayFilteredDataActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var db: SQLiteDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_filtered_data)

        listView = findViewById(R.id.listViewFiltered)
        db = SQLiteDBHelper(this, null)

        val filter = intent.getStringExtra("filter")
        val cursor = when (filter) {
            "greater" -> db.fetchDataFilteredByAge("> 30")
            "less" -> db.fetchDataFilteredByAge("< 30")
            else -> null
        }

        cursor?.let {
            val userList = mutableListOf<UserDataModel>()
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteDBHelper.COLUMN_NAME))
                    val age = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteDBHelper.COLUMN_AGE))
                    val imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(SQLiteDBHelper.COLUMN_IMAGE))

                    userList.add(UserDataModel(name, age, imageBytes))
                } while (cursor.moveToNext())
            }

            val adapter = UserAdapter(this, userList)
            listView.adapter = adapter

            cursor.close()
        }
    }
}
