package com.shinusei.headachemonitor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Inputs(modifier: Modifier = Modifier) {
    var lowInput by remember { mutableStateOf("") }
    var highInput by remember { mutableStateOf("") }
    var pulseInput by remember { mutableStateOf("") }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        OutlinedTextField(
            value = lowInput,
            onValueChange = { newText -> lowInput = newText.filter { it.isDigit() }.take(3) },
            label = { Text("Низ") },
            modifier = Modifier
                .weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = highInput,
            onValueChange = { newText -> highInput = newText.filter { it.isDigit() }.take(3) },
            label = { Text("Верх") },
            modifier = Modifier
                .weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.width(20.dp))
        OutlinedTextField(
            value = pulseInput,
            onValueChange = { newText -> pulseInput = newText.filter { it.isDigit() }.take(3) },
            label = { Text("Пульс") },
            modifier = Modifier
                .weight(1f),
            //colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color(0xFF000000)),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
    }

}
