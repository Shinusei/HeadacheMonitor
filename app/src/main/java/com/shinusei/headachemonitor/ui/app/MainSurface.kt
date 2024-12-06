package com.shinusei.headachemonitor.ui.app

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import java.util.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.shinusei.headachemonitor.R
import com.shinusei.headachemonitor.db.MainApplication
import com.shinusei.headachemonitor.db.NotesDatabase
import com.shinusei.headachemonitor.db.NotesViewModel
import org.intellij.lang.annotations.JdkConstants
import java.time.DayOfWeek
import kotlin.reflect.typeOf
import kotlin.text.format

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
    val dataList = viewModel.getAllNotes().collectAsState(initial = emptyList())

    Column {
        DatePickersRow(
            modifier = Modifier
                .wrapContentSize(Alignment.TopCenter)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
        )
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            items(dataList.value.size) { note ->
                val data = dataList.value[note].date
                val simpleDateFormat = SimpleDateFormat(
                    "EEEE",
                    Locale.getDefault()
                ) // "EEEE" pattern for full day name
                val dayOfWeekString = simpleDateFormat.format(data)

                Row() {
                    Column(){
                        Text(
                            text = "${data.day}.${data.month}.${1900 + data.year}  ${dayOfWeekString}",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .padding(bottom = 10.dp, top = 8.dp, start = 5.dp)
                        )
                        Row() {
                            Text(
                                text = "${dataList.value[note].lowPressure} / ${dataList.value[note].lowPressure}",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .padding(start = 5.dp, bottom = 5.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "${dataList.value[note].pulse}",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .padding(end = 5.dp, bottom = 5.dp)
                                    .weight(1f)
                            )
                        }
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_delete_outline_24),
                            contentDescription = "delete note"
                        )
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