package com.shinusei.headachemonitor

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Panel() {
    val systemUiController = rememberSystemUiController()
    val primaryColor = MaterialTheme.colorScheme.primaryContainer
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = primaryColor
        )
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {DatePickersRow(modifier = Modifier
                    .wrapContentSize(Alignment.BottomCenter)
                    //.fillMaxWidth()
                    .padding(end=15.dp, bottom = 5.dp)
                    //.navigationBarsPadding()
                )},
                navigationIcon = {
                    var swapIcon by remember { mutableStateOf(false) }
                    IconButton({swapIcon = !swapIcon}) {
                        Crossfade(
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
                                    contentDescription = "Вид календаря"
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
        }

    ) { innerPadding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            color = MaterialTheme.colorScheme.inverseOnSurface) {
            MainSurface()
        }
    }

}
