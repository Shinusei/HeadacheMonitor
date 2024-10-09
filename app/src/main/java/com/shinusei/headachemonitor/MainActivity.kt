package com.shinusei.headachemonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.shinusei.headachemonitor.ui.theme.HeadacheMonitorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeadacheMonitorTheme {
                Panel()
            }
        }
    }
}