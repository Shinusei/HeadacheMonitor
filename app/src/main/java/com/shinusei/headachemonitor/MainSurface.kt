package com.shinusei.headachemonitor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSurface() {
    DatePickersRow(modifier = Modifier
        .wrapContentSize(Alignment.TopCenter)
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.primaryContainer)
        .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
    )
    //TODO: shower

}