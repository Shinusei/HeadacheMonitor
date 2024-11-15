package com.shinusei.headachemonitor.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date,
    val lowPressure: Int,
    val highPressure: Int,
    val Pulse: Int
)
