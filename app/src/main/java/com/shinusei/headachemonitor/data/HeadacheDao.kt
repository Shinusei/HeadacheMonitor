package com.shinusei.headachemonitor.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface HeadacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(headache: Headache)

    @Update
    suspend fun update(headache: Headache)

    @Delete
    suspend fun delete(headache: Headache)

    @Query("SELECT * from headache ORDER BY id")
    fun getAllItems(): Flow<List<Headache>>

}