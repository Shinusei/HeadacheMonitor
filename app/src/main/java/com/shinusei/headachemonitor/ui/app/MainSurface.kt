package com.shinusei.headachemonitor.ui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shinusei.headachemonitor.db.MainApplication
import com.shinusei.headachemonitor.db.NotesDatabase
import com.shinusei.headachemonitor.db.NotesViewModel
import kotlin.reflect.typeOf

/**
 * Компонуемый элемент, представляющий основную поверхность экрана.
 *
 * Этот элемент содержит строку выбора дат ([DatePickersRow]) и заполнитель для будущего элемента "shower".
 *
 * @see DatePickersRow
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSurface(viewModel: NotesViewModel) {

    Column {
        DatePickersRow(
            modifier = Modifier
                .wrapContentSize(Alignment.TopCenter)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
        )
        val dataList = viewModel.getAllNotes().collectAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            items(dataList.value.size) { note ->
                Card {
                    Text(dataList.value[note].date.toString())
                    Text("${dataList.value[note].lowPressure} / ${dataList.value[note].lowPressure}")
                }
            }
        }
    }
}