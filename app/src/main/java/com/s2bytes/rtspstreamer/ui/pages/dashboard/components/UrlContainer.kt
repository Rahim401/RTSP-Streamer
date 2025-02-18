package com.s2bytes.rtspstreamer.ui.pages.dashboard.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.s2bytes.rtspstreamer.ui.pages.DashboardAct
import com.s2bytes.rtspstreamer.ui.pages.UiAction
import com.s2bytes.rtspstreamer.ui.pages.common.components.Container
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme

@Composable
fun UrlContainer(
    modifier: Modifier = Modifier,
    defaultVideoLink: String = "",
    onAction: (UiAction) -> Unit = {},
) {
    var videoLink by remember { mutableStateOf(defaultVideoLink) }
    val focusRequest = remember { FocusRequester() }

    Container(
        modifier, "",
        titleBottomPadding = 5.dp
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp),
                value = videoLink, label = {
                    Text(
                        text = "Enter RTSP Video link",
                        fontSize = 15.5.sp
                    )
                },
                onValueChange = {
                    videoLink = it
                },
                singleLine = true
            )
            Button(
                modifier = Modifier
                    .height(64.dp)
                    .padding(top = 7.dp)
                    .focusRequester(focusRequest),
                shape = ShapeDefaults.ExtraSmall,
                contentPadding = PaddingValues(horizontal = 15.dp),
                onClick = { onAction(DashboardAct.PlayFromUrl(videoLink)) }
            ) {
                Text(
                    text = "Go",
                    fontSize = 25.sp
                )
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    RtspStreamerTheme {
        UrlContainer()
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
private fun Preview2() {
    RtspStreamerTheme {
        UrlContainer()
    }
}