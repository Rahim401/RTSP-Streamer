package com.s2bytes.rtspstreamer.ui.pages.common.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme


@Composable
fun FilledButton(
    onClick: () -> Unit, modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) = FilledTonalButton(
    onClick, modifier,
    shape = MaterialTheme.shapes.extraSmall,
    colors = ButtonDefaults.filledTonalButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.surface,
    ), content = content
)

@Composable
fun FilledTextButton(
    text: String, modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) = FilledButton(onClick, modifier) {
    Text(
        text, style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        maxLines = 1, overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun FilledTextButton(
    text: AnnotatedString, modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) = FilledButton(onClick, modifier) {
    Text(
        text, style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        maxLines = 1, overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun FilledIconButton(
    icon: ImageVector, modifier: Modifier = Modifier,
    contentDescription: String? = null,
    onClick: () -> Unit = {}
) = androidx.compose.material3.FilledIconButton(
    onClick, modifier,
    shape = MaterialTheme.shapes.extraSmall,
    colors = IconButtonDefaults.filledIconButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.surface,
    ),
    content = { Icon(icon, contentDescription) }
)


@Preview
@Composable
private fun Preview() {
    RtspStreamerTheme {
        FilledTextButton(text = "Hello Man")
    }
}