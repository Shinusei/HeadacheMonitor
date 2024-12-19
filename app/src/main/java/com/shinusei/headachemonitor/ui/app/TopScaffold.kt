package com.shinusei.headachemonitor.ui.app

import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.shinusei.headachemonitor.R
import com.shinusei.headachemonitor.db.ExportDataToFile
import com.shinusei.headachemonitor.db.NotesViewModel

/**
 * Panel
 *
 * @param viewModel
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Panel(viewModel: NotesViewModel) {
    val openInputDialog = remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val exportDataToFile = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = {},
                actions = {
                    IconButton(onClick = {
                        exportDataToFile.value = !exportDataToFile.value

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.rounded_download_24),
                            contentDescription = "select date range",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                scrollBehavior = scrollBehavior
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
            color = MaterialTheme.colorScheme.surfaceContainer
        ) {
            MainSurface(
                viewModel = viewModel
            )
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
    when {
        exportDataToFile.value -> {
            /*if (ContextCompat.checkSelfPermission(LocalContext.current, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions((LocalContext.current as MainActivity) , permissions, 1)

            }
            else {*/
                ExportDataToFile(
                    context = LocalContext.current,
                    data = "Notes_DB"
                )
            //}
           exportDataToFile.value = false
        }
    }
}

