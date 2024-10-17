package com.shinusei.headachemonitor.data

import android.health.connect.datatypes.units.Pressure
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
data class Headache(
    @PrimaryKey(autoGenerate = true)
    val id: UShort = 0u,
    val date: Date,
    val lowPressure: Pressure,
    val highPressure: Pressure,
    val pulse: UByte
)
