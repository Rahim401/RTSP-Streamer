package com.s2bytes.rtspstreamer.ui.pages.dashboard

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.s2bytes.rtspstreamer.ui.pages.DashboardStates
import com.s2bytes.rtspstreamer.ui.pages.UiAction
import com.s2bytes.rtspstreamer.ui.pages.common.components.Container
import com.s2bytes.rtspstreamer.ui.pages.dashboard.components.UrlContainer
import com.s2bytes.rtspstreamer.ui.pages.dashboard.components.VideoContainer
import com.s2bytes.rtspstreamer.ui.pages.main.MainPage
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme

@Composable
fun DashboardPage(
    modifier: Modifier = Modifier,
    dashboardStates: DashboardStates = DashboardStates(),
    onAction: (UiAction) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Column(
        modifier.padding().padding(horizontal = 15.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        UrlContainer(
            Modifier.fillMaxWidth().padding(top = 15.dp),
            dashboardStates.defVideoLink, onAction
        )
        VideoContainer(
            Modifier.fillMaxWidth().height(300.dp),
            dashboardStates, onAction
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
//    val x = Note("Hello", "May name is billa")
    RtspStreamerTheme {
        DashboardPage(
//            fragSt = NotesList(
//                notesList = persistentListOf(x),
//                notesSelected = persistentSetOf(x.id)
//            )
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DrawerPreview2() {
    RtspStreamerTheme {
        DashboardPage(
//            fragSt = NotesList(
//                notesList = persistentListOf(x),
//                notesSelected = persistentSetOf(x.id)
//            )
        )
    }
}