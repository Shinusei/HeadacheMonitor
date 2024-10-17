package com.shinusei.headachemonitor.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Headache::class], version = 1, exportSchema = false)
abstract class HeadacheDatabase : RoomDatabase(){
    abstract fun headacheDao(): HeadacheDao

    companion object {
        @Volatile
        private var Instance: HeadacheDatabase? = null

        fun getDatabase(context: Context): HeadacheDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HeadacheDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}