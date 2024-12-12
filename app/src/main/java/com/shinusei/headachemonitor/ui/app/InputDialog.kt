package com.shinusei.headachemonitor.ui.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.shinusei.headachemonitor.db.NotesViewModel

/**
 * Input dialog
 *
 * @param onDismissRequest
 * @param onConfirmRequest
 * @param viewModel
 * @receiver
 * @receiver
 */
@Composable
fun InputDialog(onDismissRequest: () -> Unit, onConfirmRequest: () -> Unit, viewModel: NotesViewModel) {
    var lowInput by remember { mutableStateOf("") }
    var highInput by remember { mutableStateOf("") }
    var pulseInput by remember { mutableStateOf("") }
    var dateInput by remember { mutableStateOf<Long?>(null) }
    var showDatePickerInput = remember { mutableStateOf(false) }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(5.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                Modifier.padding(10.dp)
            ) {
                OutlinedTextField(
                    value = dateInput?.let { convertMillisToDate(it) } ?: "",
                    onValueChange = {  },
                    label = { Text("От") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    trailingIcon = {
                        IconButton(onClick = {
                            showDatePickerInput.value = !showDatePickerInput.value
                        }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select date"
                            )
                        }
                    },
                )
                when {
                    (showDatePickerInput.value) -> {
                        DatePickerModal(
                            onDateSelected = {
                                dateInput = it
                            },
                            onDismiss = {
                                showDatePickerInput.value = false
                            }
                        )
                    }
                }
                Row {
                    OutlinedTextField(
                        value = highInput,
                        onValueChange = { newText ->
                            highInput = newText.filter { it.isDigit() }.take(3)
                        },
                        label = { Text("Верх") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(Modifier.width(10.dp))
                    OutlinedTextField(
                        value = lowInput,
                        onValueChange = { newText ->
                            lowInput = newText.filter { it.isDigit() }.take(3)
                        },
                        label = { Text("Низ") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                    )
                    Spacer(Modifier.width(10.dp))
                    OutlinedTextField(
                        value = pulseInput,
                        onValueChange = { newText ->
                            pulseInput = newText.filter { it.isDigit() }.take(3)
                        },
                        label = { Text("Пульс") },
                        modifier = Modifier.weight(1f),
                        //colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color(0xFF000000)),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        )
                    )
                }

            }

            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,

            ) {
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Отмена")
                }
                TextButton(
                    onClick = {
                        viewModel.addNotes(
                            dateInput,
                            lowInput,
                            highInput,
                            pulseInput
                        )
                        lowInput = ""
                        highInput = ""
                        pulseInput = ""
                        onConfirmRequest()
                    },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Добавить")
                }
            }
        }
    }
}