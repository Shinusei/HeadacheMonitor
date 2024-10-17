package com.shinusei.headachemonitor.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Headache(
    @PrimaryKey(autoGenerate = true)
    val id: UShort = 0u,
    val date: String,
    val lowPressure: UShort,
    val highPressure: UShort,
    val pulse: UByte
)
