package com.s2bytes.rtspstreamer.ui.pages.dashboard.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.s2bytes.rtspstreamer.fromHtml
import com.s2bytes.rtspstreamer.ui.pages.common.components.Container
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme

@Composable
fun NotesContainer(modifier: Modifier = Modifier) {
    Container(modifier, "Some Words on This") {
        Column(verticalArrangement = Arrangement.spacedBy(7.5.dp)) {
            Row {
                Text(text = "1. ", lineHeight = 19.sp)
                Text(
                    text = "Most of the RTSP streams are <b>UnPause-able or Non-Interactive</b>, On those cases Pausing might not work!".fromHtml(),
                    lineHeight = 19.sp
                )
            }
            Row {
                Text(text = "2. ", lineHeight = 19.sp)
                Text(
                    text = "But you can use the <b>Stop Button</b> instead, Which behaves in a similar manner!".fromHtml(),
                    lineHeight = 19.sp
                )
            }
            Row {
                Text(text = "3. ", lineHeight = 19.sp)
                Text(
                    text = "You can Reconnect with <b>Go Button</b> in case of any Playback issue!".fromHtml(),
                    lineHeight = 19.sp
                )
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    RtspStreamerTheme {
        NotesContainer(Modifier)
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
private fun Preview2() {
    RtspStreamerTheme {
        NotesContainer()
    }
}