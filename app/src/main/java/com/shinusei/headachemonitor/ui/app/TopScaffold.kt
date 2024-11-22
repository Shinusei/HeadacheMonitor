package com.shinusei.headachemonitor.ui.app

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import com.shinusei.headachemonitor.R
import com.shinusei.headachemonitor.db.NotesViewModel

/**
 * Компонуемый элемент, представляющий основную панель приложения.
 *
 * Этот элемент содержит TopAppBar, FloatingActionButton и Surface для отображения основного контента.
 * Он также управляет состоянием диалогового окна ввода.
 *
 * @param viewModel экземпляр [NotesViewModel], используемый для доступа к данным и логике приложения.
 *
 * @see NotesViewModel
 * @see TopAppBar
 * @see LargeFloatingActionButton
 * @see Surface
 * @see InputDialog
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Panel(viewModel: NotesViewModel) {
    val primaryColor = MaterialTheme.colorScheme.surface
    //val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var openInputDialog = remember { mutableStateOf(false) }

    var offset by remember { mutableStateOf(0f) }

    Scaffold(
        modifier = Modifier,
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
                .fillMaxSize()
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
                onConfirmRequest = { openInputDialog.value = false },
                viewModel = viewModel
            )
        }
    }
}

