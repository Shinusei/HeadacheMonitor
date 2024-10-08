package com.shinusei.headachemonitor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Inputs(modifier: Modifier = Modifier) {
    var LowInput by remember { mutableStateOf("") }
    var HighInput by remember { mutableStateOf("") }
    var PulseInput by remember { mutableStateOf("") }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        OutlinedTextField(
            value = LowInput,
            onValueChange = { LowInput = it },
            label = { Text("Низ") },
            modifier = Modifier
                .weight(1f)
                .padding(start = 5.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = HighInput,
            onValueChange = { HighInput = it },
            label = { Text("Верх") },
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = PulseInput,
            onValueChange = { PulseInput = it },
            label = { Text("Пульс") },
            modifier = Modifier
                .weight(1f)
                .padding(start = 5.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
    }

}
