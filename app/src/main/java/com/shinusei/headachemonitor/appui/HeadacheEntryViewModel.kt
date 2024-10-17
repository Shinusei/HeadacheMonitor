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

    private fun validateInput(uiState: HeadacheDetails = headacheUiState.): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}

data class HeadacheUiState(
    val itemDetails: HeadacheDetails = HeadacheDetails(),
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

fun Headache.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

fun Headache.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    headacheDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun Headache.toItemDetails(): HeadacheDetails = HeadacheDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)