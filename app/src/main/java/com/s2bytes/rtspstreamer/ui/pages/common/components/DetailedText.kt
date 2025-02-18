package com.s2bytes.rtspstreamer.ui.pages.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme

@Composable
fun DetailedText(
    text: String,
    modifier: Modifier = Modifier,
    detail: String? = null, icon: ImageVector? = null,
    color: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    Row(modifier.padding(horizontal = 25.dp), Arrangement.spacedBy(20.dp), Alignment.CenterVertically) {
        if(icon != null) Icon(icon, text,
            Modifier
                .padding(start = 10.dp)
                .size(25.dp), tint = color)
        Column(
            Modifier
                .weight(1f)
                .padding(
                    vertical = if (detail == null) 14.dp else 8.dp
                ), Arrangement.Center
        ) {
            Text(
                text, style = MaterialTheme.typography.bodyLarge, color = color,
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )
            if(detail != null) Text(
                detail, style = MaterialTheme.typography.labelSmall,
                color = color.copy(alpha = 0.8f), textAlign = TextAlign.Start
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    RtspStreamerTheme {
        Column(
            Modifier.fillMaxWidth(.9f),
            Arrangement.spacedBy(2.dp)
        ) {
            DetailedText("Ookla")
            DetailedText("Test", Modifier, "My name is the test app")
            DetailedText("Ookla")
        }
    }
}