package com.shinusei.headachemonitor.ui.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 * Компонуемый элемент, представляющий строку с двумя полями ввода для выбора диапазона дат.
 *
 * Этот элемент содержит два [OutlinedTextField] для ввода начала и конца периода.
 *
 * @see Row
 * @see OutlinedTextField
 * @see Spacer
 */
@Composable
fun DatePickersRow(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        var startRange by remember { mutableStateOf("") }

        OutlinedTextField(
            value = startRange,
            onValueChange = { newText -> startRange = newText.filter { it.isDigit() } },
            label = { Text("Начало периода") },
            modifier = Modifier
                .weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
        )
        Spacer(Modifier.width(10.dp))
        OutlinedTextField(
            value = startRange,
            onValueChange = { newText -> startRange = newText.filter { it.isDigit() } },
            label = { Text("Конец периода") },
            modifier = Modifier
                .weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            maxLines = 1
        )
    }
}