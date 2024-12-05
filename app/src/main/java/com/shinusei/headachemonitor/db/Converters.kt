package com.shinusei.headachemonitor.db

import androidx.room.TypeConverter
import java.util.Date

/**
 * Converters
 *
 * @constructor Create empty Converters
 */
class Converters {
    /**
     * From date
     *
     * @param date
     * @return
     */
    @TypeConverter
    fun fromDate(date: Date) : Long {
        return date.time
    }

    /**
     * To date
     *
     * @param time
     * @return
     */
    @TypeConverter
    fun toDate(time: Long) : Date {
        return Date(time)
    }

    /**
     * To int
     *
     * @param string
     * @return
     */
    @TypeConverter
    fun toInt(string: String) : Int {
        return string.toInt()
    }
}