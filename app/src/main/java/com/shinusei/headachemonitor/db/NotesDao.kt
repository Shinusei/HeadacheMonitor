package com.shinusei.headachemonitor.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Notes dao
 *
 * @constructor Create empty Notes dao
 */
@Dao
interface NotesDao {
    /**
     * Get all notes
     *
     * @return
     */
    @Query("SELECT * FROM Notes")
    fun getAllNotes(): LiveData<List<Notes>>

    /**
     * Add notes
     *
     * @param notes
     */
    @Insert
    fun addNotes(notes: Notes)

    /**
     * Delete notes
     *
     * @param id
     */
    @Query("DELETE FROM Notes WHERE id = :id")
    fun deleteNotes(id: Int)

    @Query("SELECT * FROM Notes")
    fun getAllRecords(): Flow<List<Notes>>
}