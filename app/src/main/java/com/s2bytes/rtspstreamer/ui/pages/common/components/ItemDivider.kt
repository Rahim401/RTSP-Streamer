package com.s2bytes.rtspstreamer.ui.pages.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme

@Composable
fun StdDivider(modifier: Modifier) = HorizontalDivider(
    modifier
        .padding(vertical = 2.5.dp)
        .clip(MaterialTheme.shapes.medium),
    thickness = 0.75.dp, color = MaterialTheme.colorScheme.outline
)


@Preview
@Composable
private fun Preview() {
    RtspStreamerTheme {
        Column {
            StdDivider(modifier = Modifier.fillMaxWidth())
        }
    }
}