package com.s2bytes.rtspstreamer.ui.pages.main.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Deselect
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.s2bytes.rtspstreamer.ui.pages.MainAct
import com.s2bytes.rtspstreamer.ui.pages.UiAction
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme

@Composable
private fun AppBarButton(icon: ImageVector, onClick:()->Unit){
    IconButton(onClick, modifier = Modifier.size(50.dp)){
        Icon(
            icon, null,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarOld(
    inSelectMode: Boolean = false,
    onMenuPressed: () -> Unit = {},
    onSelectPressed: () -> Unit = {},
    onUnSelectPressed: () -> Unit = {},
) = CenterAlignedTopAppBar(
    title = { Text("Notes App") },
    navigationIcon = {
        AnimatedVisibility(inSelectMode) {
            AppBarButton(Icons.Filled.SelectAll, onSelectPressed)
        }
        AnimatedVisibility(!inSelectMode) {
            AppBarButton(Icons.Filled.Menu, onMenuPressed)
        }
    },
    actions = {
        AnimatedVisibility(inSelectMode) {
            AppBarButton(Icons.Filled.Deselect, onUnSelectPressed)
        }
    },
    colors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary,
        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
    )
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(onAction: (UiAction) -> Unit = {}) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                "RTSP Stream",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            AppBarButton(Icons.Filled.Menu) { onAction(MainAct.MenuButtonPrs) }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun AppBarPreview() {
    RtspStreamerTheme {
//        TopAppBar2 {  }
        TopAppBar(
//            NotesList(notesSelected = persistentListOf())
//            NotesEdit(Note(""))
        )
    }
}