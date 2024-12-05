package com.shinusei.headachemonitor.db

import android.app.Application
import androidx.room.Room

/**
 * Main application
 *
 * @constructor Create empty Main application
 */
class MainApplication : Application(){
    companion object{
        lateinit var notesDatabase: NotesDatabase
    }

    override fun onCreate() {
        super.onCreate()
        notesDatabase = Room.databaseBuilder(this, NotesDatabase::class.java, "notes_db").build()
    }
}