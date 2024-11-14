package com.shinusei.headachemonitor.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class NotesViewModel : ViewModel() {

    val notesDao = MainApplication.notesDatabase.getNotesDao()
    val AllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    fun addNotes(title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            notesDao.addNotes(
                Notes(
                    title = title,
                    description = description,
                    date = Date.from(Instant.now())
                )
            )
        }
    }

    fun deleteNotes(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            notesDao.deleteNotes(id)
        }
    }

}