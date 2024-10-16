package com.example.learning226.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase: RoomDatabase() {

    abstract fun ContactDAO(): ContactDAO
}