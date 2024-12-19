package com.shinusei.headachemonitor.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

/**
 * Notes view model
 *
 * @constructor Create empty Notes view model
 */
class NotesViewModel : ViewModel() {
    val notesDao = MainApplication.notesDatabase.getNotesDao()

    /**
     * Add notes
     *
     * @param dateInput
     * @param lowPressure
     * @param highPressure
     * @param pulse
     */
    fun addNotes(dateInput: Long?, lowPressure: String, highPressure: String, pulse: String) {
        viewModelScope.launch(Dispatchers.IO) {
            notesDao.addNotes(
                Notes(
                    date = if (dateInput == null) {
                        Date.from(Instant.now())
                    } else {
                        Date(dateInput)
                    },
                    lowPressure = if (lowPressure == ""){
                        -1
                    } else {
                        lowPressure.toInt()
                    },
                    highPressure = if (highPressure == ""){
                        -1
                    } else {
                        highPressure.toInt()
                    },
                    pulse = if (pulse == ""){
                        -1
                    } else {
                        pulse.toInt()
                    }
                )
            )
        }
    }

    /**
     * Get all notes
     *
     * @param startRange
     * @param endRange
     * @return
     */
    fun getAllNotes(startRange: Long?, endRange: Long?): Flow<List<Notes>> {
        if (startRange == null && endRange == null) {
            return notesDao.getAllRecords()
        } else if (startRange != null && endRange == null) {
            return notesDao.getRecordsStart(Date(startRange))
        } else if (startRange == null && endRange != null) {
            return notesDao.getRecordsEnd(Date(endRange))
        } else {
            if (startRange!! < endRange!!) {
                return notesDao.getRecordsAsc(Date(startRange), Date(endRange))
            }
        }
        return notesDao.getRecordsDesc(Date(startRange), Date(endRange))
    }

    /**
     * Delete notes
     *
     * @param id
     */
    fun deleteNotes(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            notesDao.deleteNotes(id)
        }
    }
}