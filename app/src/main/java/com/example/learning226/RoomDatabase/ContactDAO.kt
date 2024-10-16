package com.example.learning226.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDAO {

    @Insert
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contact")
    fun getContact() : LiveData<List<Contact>>
}