package com.s2bytes.rtspstreamer.ui.pages.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BasicTopAppBar(
    title:String = "Some Page",
    onBackPressed: () -> Unit = {}
) = TopAppBar(
    title = {
        Text(
            title, style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 10.dp),
        )
    },
    navigationIcon = {
        IconButton(onClick = onBackPressed) {
            Icon(Icons.Default.ArrowBack, null)
        }
    },
    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface
    )
)



@Preview
@Composable
private fun Preview() {
    RtspStreamerTheme {
        BasicTopAppBar()
    }
}