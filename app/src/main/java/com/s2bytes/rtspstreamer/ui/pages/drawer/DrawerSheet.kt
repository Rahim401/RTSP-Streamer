package com.s2bytes.rtspstreamer.ui.pages.drawer

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.s2bytes.rtspstreamer.ui.pages.DrawerAct
import com.s2bytes.rtspstreamer.ui.pages.DrawerStates
import com.s2bytes.rtspstreamer.ui.pages.UiAction
import com.s2bytes.rtspstreamer.ui.pages.drawer.components.DrawerItemCB
import com.s2bytes.rtspstreamer.ui.pages.drawer.components.ProfileHeader
import com.s2bytes.rtspstreamer.ui.pages.drawer.components.DrawerItem
import com.s2bytes.rtspstreamer.ui.pages.drawer.components.ItemDivider
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme


@Composable
fun DrawerSheet(
    drawerStates: DrawerStates = DrawerStates(),
    onAction: (UiAction) -> Unit = {}
) {
    ModalDrawerSheet(modifier = Modifier.width(300.dp),){
        Column(Modifier.verticalScroll(rememberScrollState())){
            Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            ProfileHeader(drawerStates.userName) { onAction(DrawerAct.MediaPrs(it)) }

            Box(
                Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primary.copy(.5f)
                    )
            ){
                Divider(
                    Modifier.fillMaxWidth(drawerStates.noOfThisStreams / 25f),
                    5.dp, MaterialTheme.colorScheme.primary
                )
            }

            DrawerItem("This is Stream No: ${drawerStates.noOfThisStreams}") {
                onAction(DrawerAct.NoOfStreamPrs)
            }
            ItemDivider()

            DrawerItemCB("Optimize for Live", drawerStates.isOptimizedForLive) {
                onAction(DrawerAct.OptimizeForLive(it))
            }
            DrawerItemCB("Stream using TCP", drawerStates.isStreamingWithTCP) {
                onAction(DrawerAct.ForceUsingTCP(it))
            }
        }
//        Spacer(modifier = Modifier.fillMaxSize())
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Built with LibVLC\nFor Vecros Assignment\n",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(0.8f),
                modifier = Modifier.align(Alignment.BottomCenter)
                    .padding(bottom = 2.5.dp)
            )
        }
    }
}




@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DrawerPreview() {
    RtspStreamerTheme {
        DrawerSheet()
    }
}
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DrawerPreview2() {
    RtspStreamerTheme {
        DrawerSheet()
    }
}