package com.shinusei.headachemonitor

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSurface() {
    /*DatePickersRow(modifier = Modifier
        .wrapContentSize(Alignment.TopCenter)
        .fillMaxWidth()
        .padding(15.dp)
        .navigationBarsPadding()
    )*/
    //TODO: shower
    Inputs(
        modifier = Modifier
            .wrapContentSize(Alignment.BottomCenter)
            .fillMaxWidth()
            .padding(15.dp)
            .navigationBarsPadding()
    )
}
