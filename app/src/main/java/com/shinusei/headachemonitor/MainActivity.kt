package com.shinusei.headachemonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.shinusei.headachemonitor.db.NotesViewModel
import com.shinusei.headachemonitor.ui.app.Panel
import com.shinusei.headachemonitor.ui.theme.HeadacheMonitorTheme
import java.util.Locale


/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        resources.configuration.setLocale(Locale("ru", "RU"))
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        setContent {
            HeadacheMonitorTheme {
                Panel(viewModel)
            }
        }
    }
}