package com.shinusei.headachemonitor.appui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.shinusei.headachemonitor.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Panel() {
    val systemUiController = rememberSystemUiController()
    val primaryColor = MaterialTheme.colorScheme.surface
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var openInputDialog = remember { mutableStateOf(false) }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = primaryColor
        )
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                ),
                title = {},
                navigationIcon = {
                    var swapIcon by remember { mutableStateOf(false) }
                    IconButton({ swapIcon = !swapIcon }) {
                        Crossfade(
                            label = "Crossfade",
                            targetState = swapIcon,
                            animationSpec = tween(durationMillis = 300)
                        ) { showFirst ->
                            if (showFirst) {
                                Icon(
                                    painter = painterResource(id = R.drawable.rounded_lists_24),
                                    contentDescription = "Вид списка"
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.rounded_calendar_month_24),
                                    contentDescription = "Вид календарь"
                                )
                            }
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.rounded_download_24),
                            contentDescription = "select date range"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {
                    openInputDialog.value = true
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.rounded_add_2_24),
                        contentDescription = "Вид списка"
                    )
                },
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.inverseOnSurface
        ) {
            MainSurface()
        }
    }
    when {
        openInputDialog.value -> {
            InputDialog(
                onDismissRequest = { openInputDialog.value = false },
                onConfirmation = {
                    openInputDialog.value = false
                },
            )
        }
    }
}

