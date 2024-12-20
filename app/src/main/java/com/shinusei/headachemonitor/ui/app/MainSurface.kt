package com.shinusei.headachemonitor.ui.app

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shinusei.headachemonitor.R
import com.shinusei.headachemonitor.db.NotesViewModel
import java.util.Date
import java.util.Locale


/**
 * Main surface
 *
 * @param viewModel
 */
@Composable
fun MainSurface(viewModel: NotesViewModel) {
    var startRange by remember { mutableStateOf<Long?>(null) }
    val showDatePickerStart = remember { mutableStateOf(false) }
    var endRange by remember { mutableStateOf<Long?>(null) }
    val showDatePickerEnd = remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .wrapContentSize(Alignment.TopCenter)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surfaceContainer)
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = startRange?.let { convertMillisToDate(it) } ?: "",
                onValueChange = {  },
                label = { Text("От") },
                readOnly = true,
                modifier = Modifier
                    .weight(1f),
                maxLines = 1,
                trailingIcon = {
                    IconButton(onClick = {
                        showDatePickerStart.value = !showDatePickerStart.value
                    }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
            )
            when {
                (showDatePickerStart.value) -> {
                    DatePickerModal(
                        onDateSelected = {
                            startRange = it
                        },
                        onDismiss = {
                            showDatePickerStart.value = false
                        }
                    )
                }
            }
            IconButton(onClick = {
                startRange = endRange.also { endRange = startRange }
            },
                modifier = Modifier.padding(top = 5.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.rounded_swap_horiz_24),
                    contentDescription = "swap dates"
                )
            }
            OutlinedTextField(
                value = endRange?.let { convertMillisToDate(it) } ?: "",
                onValueChange = {  },
                label = { Text("До") },
                readOnly = true,
                modifier = Modifier
                    .weight(1f),
                maxLines = 1,
                trailingIcon = {
                    IconButton(onClick = {
                        showDatePickerEnd.value = !showDatePickerEnd.value
                    }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
            )
            when {
                (showDatePickerEnd.value) -> {
                    DatePickerModal(
                        onDateSelected = {
                            endRange = it
                        },
                        onDismiss = {
                            showDatePickerEnd.value = false
                        }
                    )
                }
            }
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
        val dataList = viewModel.getAllNotes(startRange, endRange).collectAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
                //.background(color = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            items(dataList.value.size) { note ->
                val data = dataList.value[note].date
                val simpleDateFormat = SimpleDateFormat(
                    "EEEE",
                    Locale("ru", "RU")
                )
                val dayOfWeekString = simpleDateFormat.format(data)
                Card (
                    modifier = Modifier
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer),
                ){
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Text(
                                text = "${convertMillisToDate(data.time)}  $dayOfWeekString",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier
                                    .padding(bottom = 8.dp, top = 8.dp, start = 5.dp),
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Row {
                                Column {
                                    Text(
                                        text = "SYS ",
                                        style = MaterialTheme.typography.bodySmall,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier
                                            //.padding(start = 20.dp)
                                            .wrapContentSize(Alignment.CenterEnd)
                                            .width(45.dp),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Text(
                                        text = "${if (dataList.value[note].highPressure == -1) "-" else (dataList.value[note].highPressure)} ",
                                        style = MaterialTheme.typography.titleLarge,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier
                                            .padding(start = 5.dp, bottom = 5.dp)
                                            .width(45.dp),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                }
                                Column {
                                    Text("/",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Text("/",
                                        style = MaterialTheme.typography.titleLarge,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                }
                                Column {
                                    Text(
                                        text = "DIA",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .wrapContentSize(Alignment.CenterEnd),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Text(
                                        text = "${if (dataList.value[note].lowPressure == -1) "-" else (dataList.value[note].lowPressure)} ",
                                        style = MaterialTheme.typography.titleLarge,
                                        modifier = Modifier
                                            .padding(start = 5.dp, bottom = 5.dp),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                }
                                Column {
                                    Text(
                                        text = " Пульс",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .wrapContentSize(Alignment.CenterEnd),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Text(
                                        text = " ${if (dataList.value[note].pulse == -1) "-" else (dataList.value[note].pulse)} ",
                                        style = MaterialTheme.typography.titleLarge,
                                        modifier = Modifier
                                            .padding(start = 5.dp, bottom = 5.dp),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                }
                            }
                        }
                        IconButton(
                            onClick = {
                                viewModel.deleteNotes(dataList.value[note].id)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.CenterEnd)
                                .align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_delete_outline_24),
                                contentDescription = "delete note"
                            )
                        }
                    }
                }
                HorizontalDivider()
            }
            items(1) {
                Spacer(modifier = Modifier.height(150.dp))
            }
        }
    }
}

/**
 * Convert millis to date
 *
 * @param millis
 * @return
 */
fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}