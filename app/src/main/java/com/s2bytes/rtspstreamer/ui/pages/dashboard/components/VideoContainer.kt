package com.s2bytes.rtspstreamer.ui.pages.dashboard.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.s2bytes.rtspstreamer.ui.pages.DashboardAct
import com.s2bytes.rtspstreamer.ui.pages.UiAction
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme
import org.videolan.libvlc.util.VLCVideoLayout

@Composable
fun VideoContainer(
    modifier: Modifier = Modifier,
    onAction: (UiAction) -> Unit = {},
) {
    Column(
        modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        val backColor = MaterialTheme.colorScheme.primary.value.toInt()
        AndroidView(
            factory = { VLCVideoLayout(it) },
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { view ->
            view.background = ColorDrawable(Color.WHITE)
            onAction(DashboardAct.VideoViewCreated(view))
//            view.setOnD
        }
        Row(
            Modifier
                .fillMaxWidth()
                .height(70.dp)) {

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