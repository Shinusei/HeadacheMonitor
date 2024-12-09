package com.shinusei.headachemonitor.ui.app

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.shinusei.headachemonitor.R

/**
 * Date pickers row
 *
 * @param modifier
 */
@Composable
fun DatePickersRow(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
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
        IconButton(onClick = {}, modifier = Modifier.padding(top = 5.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.rounded_swap_horiz_24),
                contentDescription = "swap dates"
            )
        }
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