package com.shinusei.headachemonitor.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.Date

/**
 * Notes dao
 *
 * @constructor Create empty Notes dao
 */
@Dao
interface NotesDao {
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

    /**
     * Get all records
     *
     * @return
     */
    @Query("SELECT * FROM Notes ORDER BY date")
    fun getAllRecords(): Flow<List<Notes>>

    /**
     * Get records asc
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Query("SELECT * FROM Notes WHERE date BETWEEN :startDate AND :endDate ORDER BY date ASC")
    fun getRecordsAsc(startDate: Date, endDate: Date): Flow<List<Notes>>

    /**
     * Get records desc
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Query("SELECT * FROM Notes WHERE date BETWEEN :endDate AND :startDate ORDER BY date DESC")
    fun getRecordsDesc(startDate: Date, endDate: Date): Flow<List<Notes>>

    /**
     * Get records end
     *
     * @param endDate
     * @return
     */
    @Query("SELECT * FROM Notes WHERE date < :endDate ORDER BY date")
    fun getRecordsEnd(endDate: Date): Flow<List<Notes>>

    /**
     * Get records start
     *
     * @param startDate
     * @return
     */
    @Query("SELECT * FROM Notes WHERE date > :startDate ORDER BY date")
    fun getRecordsStart(startDate: Date): Flow<List<Notes>>
}