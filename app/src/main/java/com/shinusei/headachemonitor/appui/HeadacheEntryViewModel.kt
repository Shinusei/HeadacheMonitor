package com.shinusei.headachemonitor.appui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shinusei.headachemonitor.data.*
import java.sql.Date
import java.text.NumberFormat

class ItemEntryViewModel(private val headachesRepository: HeadachesRepository) : ViewModel() {

    var headacheUiState by mutableStateOf(HeadacheUiState())
        private set

    fun updateUiState(headacheDetails: HeadacheDetails) {
        headacheUiState =
            HeadacheUiState(headacheDetails = headacheDetails, isEntryValid = validateInput(headacheDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            headachesRepository.insertItem(headacheUiState.headacheDetails.toItem())
        }
    }

    private fun validateInput(uiState: HeadacheDetails = headacheUiState.headacheDetails): Boolean {
        return with(uiState) {
            date.isNotBlank()
        }
    }
}

data class HeadacheUiState(
    val headacheDetails: HeadacheDetails = HeadacheDetails(),
    val isEntryValid: Boolean = false
)

data class HeadacheDetails(
    val id: UShort = 0u,
    val date: String = "",
    val lowPressure: UShort = 0u,
    val highPressure: UShort = 0u,
    val pulse: UByte = 0u,
)

fun HeadacheDetails.toItem(): Headache = Headache(
    id = id,
    date = date,
    lowPressure = lowPressure,
    highPressure =  highPressure,
    pulse = pulse,
)

fun Headache.toHeadacheUiState(isEntryValid: Boolean = false): HeadacheUiState = HeadacheUiState (
    headacheDetails = this.toHeadacheDetails(),
    isEntryValid = isEntryValid
)

fun Headache.toHeadacheDetails(): HeadacheDetails = HeadacheDetails(
    id = id.toUShort(),
    date = date.toString(),
    lowPressure = lowPressure.toUShort(),
    highPressure =  highPressure.toUShort(),
    pulse = pulse.toUByte(),
)