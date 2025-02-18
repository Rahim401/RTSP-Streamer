package com.s2bytes.rtspstreamer.ui.pages.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme

@Composable
fun SmallContainer(
    modifier: Modifier = Modifier,
    title:String = "Small Container",
    titleStyle: TextStyle = MaterialTheme.typography.labelMedium,
    content: @Composable ColumnScope.()->Unit = {}
) = Column(
    modifier.clip(MaterialTheme.shapes.small)
        .background(MaterialTheme.colorScheme.primaryContainer)
        .padding(vertical = 7.5.dp)
) {
    if(title.isNotEmpty()) Text(
        title, style = titleStyle,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        overflow = TextOverflow.Ellipsis, maxLines = 1, textAlign = TextAlign.Center,
        modifier = Modifier.padding(15.dp, 2.5.dp, 15.dp, 5.dp)//.fillMaxWidth()
    )
    content(this)
}


@Preview
@Composable
private fun Preview() {
    RtspStreamerTheme {
        SmallContainer(Modifier.fillMaxWidth())
    }
}