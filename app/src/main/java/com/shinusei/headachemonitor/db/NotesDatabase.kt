package com.shinusei.headachemonitor.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(Converters::class)
@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase(){
    companion object {
        const val NAME = "Notes_DB"
    }

    abstract fun getNotesDao() : NotesDao
}