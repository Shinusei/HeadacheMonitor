package com.shinusei.headachemonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.shinusei.headachemonitor.db.NotesViewModel
import com.shinusei.headachemonitor.ui.app.Panel
import com.shinusei.headachemonitor.ui.theme.HeadacheMonitorTheme

/**
 * Главная активность приложения Headache Monitor.
 *
 * Эта активность отвечает за отображение основного пользовательского интерфейса приложения.
 * Она создает экземпляр [NotesViewModel] и передает его в компонуемый элемент [Panel].
 *
 * @see NotesViewModel
 * @see Panel
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        setContent {
            HeadacheMonitorTheme {
                Panel(viewModel)
            }
        }
    }
}