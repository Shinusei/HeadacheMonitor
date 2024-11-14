package com.shinusei.headachemonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.shinusei.headachemonitor.appui.Panel
import com.shinusei.headachemonitor.db.NotesViewModel
import com.shinusei.headachemonitor.ui.theme.HeadacheMonitorTheme


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