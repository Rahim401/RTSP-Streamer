package com.s2bytes.rtspstreamer.ui.pages.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme

@Composable
fun Container(
    modifier: Modifier = Modifier,
    title:String = "", titleBottomPadding: Dp = 5.dp,
    titleStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    contentPadding: PaddingValues = PaddingValues(15.dp, 10.dp, 15.dp, 15.dp),
    content: @Composable ColumnScope.()->Unit = {}
) = Column(
    modifier
        .clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.primaryContainer)
        .padding(contentPadding)
) {
    if(title.isNotEmpty()) Text(
        title, style = titleStyle,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        overflow = TextOverflow.Ellipsis, maxLines = 1,
        modifier = Modifier.padding(bottom = titleBottomPadding)
    )
    content(this)
}



@Preview
@Composable
private fun Preview() {
    RtspStreamerTheme {
        Container(Modifier.fillMaxWidth(.9f), "Container ")
    }
}