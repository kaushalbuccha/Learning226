package com.example.learning226.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteDBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DB_NAME, factory, DB_VERSION) {

    companion object {
        private const val DB_NAME = "learningDB226"
        private const val DB_VERSION = 1

        const val TABLE_NAME = "Userdata"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
        const val COLUMN_IMAGE = "image"  // New column for image storage
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_AGE TEXT,
                $COLUMN_IMAGE BLOB  -- Column to store image as byte array
            );
        """
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Method to add data to the database (name, age, and image)
    fun addData(Name: String, Age: String, imageBytes: ByteArray): Long {
        val values = ContentValues()
        values.put(COLUMN_NAME, Name)
        values.put(COLUMN_AGE, Age)
        values.put(COLUMN_IMAGE, imageBytes)  // Storing image as byte array

        val db = this.writableDatabase
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result
    }

    // Method to fetch data from the database
    fun fetchData(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    // Method to delete all data from the database
    fun delAll() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
        db.close()
    }
}
