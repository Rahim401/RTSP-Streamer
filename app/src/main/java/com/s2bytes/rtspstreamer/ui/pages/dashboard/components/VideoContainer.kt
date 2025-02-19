package com.s2bytes.rtspstreamer.ui.pages.dashboard.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.s2bytes.rtspstreamer.application.MediaState
import com.s2bytes.rtspstreamer.ui.pages.DashboardAct
import com.s2bytes.rtspstreamer.ui.pages.DashboardStates
import com.s2bytes.rtspstreamer.ui.pages.UiAction
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme
import org.videolan.libvlc.util.VLCVideoLayout

@Composable
fun VideoContainer(
    modifier: Modifier = Modifier,
    dashSt: DashboardStates = DashboardStates(),
    onAction: (UiAction) -> Unit = {},
) {
    Column(
        modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        AndroidView(
            factory = { VLCVideoLayout(it) },
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { view ->
            view.background = ColorDrawable(Color.WHITE)
            onAction(DashboardAct.VideoViewCreated(view))
        }


        Row(
            Modifier
                .fillMaxWidth()
                .height(75.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Button(
                onClick = { onAction(DashboardAct.PauseOrPlayedVideo) },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f), shape = RectangleShape
            ) {
                val iconToShow = when(dashSt.playbackState) {
                    MediaState.Playing -> Icons.Default.Pause
                    else -> Icons.Default.PlayArrow
                }
                Icon(
                    iconToShow, "Play/Pause Playback",
                    Modifier.size(45.dp)
                )
            }

            val stopButtonWeight by animateFloatAsState(
                targetValue = if(dashSt.playbackState == MediaState.Playing) 0.5f
                else 0f, label = "Stop Button Visibility", 
            )
            if(stopButtonWeight > 0f) Button(
                onClick = { onAction(DashboardAct.StopVideo) },
                modifier = Modifier.fillMaxHeight().weight(stopButtonWeight),
                shape = RectangleShape
            ) {
                Icon(
                    Icons.Default.Stop, "Stop Playback",
                    Modifier.size(45.dp)
                )
            }
        }
    }
    DisposableEffect(key1 = Unit) {
        onDispose {
            onAction(DashboardAct.VideoViewDestroyed)
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    RtspStreamerTheme {
        VideoContainer(Modifier)
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
private fun Preview2() {
    RtspStreamerTheme {
        VideoContainer()
    }
}