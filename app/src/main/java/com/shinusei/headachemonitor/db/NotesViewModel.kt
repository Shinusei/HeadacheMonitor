package com.shinusei.headachemonitor.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
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
    val AllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    /**
     * Add notes
     *
     * @param lowPressure
     * @param highPressure
     * @param pulse
     */
    fun addNotes(lowPressure: Int, highPressure: Int, pulse: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            notesDao.addNotes(
                Notes(
                    date = Date.from(Instant.now()),
                    lowPressure = lowPressure,
                    highPressure = highPressure,
                    Pulse = pulse
                )
            )
        }
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