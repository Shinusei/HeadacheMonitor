package com.shinusei.headachemonitor.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes")
    fun getAllNotes(): LiveData<List<Notes>>

    @Insert
    fun addNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id = :id")
    fun deleteNotes(id: Int)
}