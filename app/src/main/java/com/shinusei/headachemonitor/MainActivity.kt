package com.shinusei.headachemonitor

import android.R
import android.icu.util.Calendar
import android.opengl.Visibility
import android.os.Bundle
import android.widget.CalendarView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shinusei.headachemonitor.ui.theme.HeadacheMonitorTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.text.format

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeadacheMonitorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    //Calendar()
                    DiceRollerApp()
                }

            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calendar(){
    val dateState = rememberDatePickerState()
    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    Column (horizontalAlignment = Alignment.CenterHorizontally){
        DatePicker(state = dateState)
        Text("Selected date: " +
                "${dateState.selectedDateMillis?.let {
                    val instant = Instant.ofEpochMilli(it)
                    val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                    dateFormatter.format(date)
                }}")
    }
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var txt = "123"

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {  },
        ) {
            Text(text = txt, style = MaterialTheme.typography.bodyLarge )
        }
    }
}

