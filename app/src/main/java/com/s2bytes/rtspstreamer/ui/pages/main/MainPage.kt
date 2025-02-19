package com.s2bytes.rtspstreamer.ui.pages.main


import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.s2bytes.rtspstreamer.ui.pages.DashboardStates
import com.s2bytes.rtspstreamer.ui.pages.DrawerStates
import com.s2bytes.rtspstreamer.ui.pages.MainAct
import com.s2bytes.rtspstreamer.ui.pages.MainStates
import com.s2bytes.rtspstreamer.ui.pages.UiAction
import com.s2bytes.rtspstreamer.ui.pages.dashboard.DashboardPage
import com.s2bytes.rtspstreamer.ui.pages.drawer.DrawerSheet
import com.s2bytes.rtspstreamer.ui.pages.main.components.TopAppBar
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme
import kotlinx.coroutines.launch

@Composable
fun MainPage(
    modifier: Modifier = Modifier,
    mainSt: MainStates = MainStates(),
    drawerSt: DrawerStates = DrawerStates(),
    dashboardSt: DashboardStates = DashboardStates(),
    onAction: (UiAction) -> Unit = {}
) {
    val uiScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = { DrawerSheet(drawerSt, onAction) },
        drawerState = drawerState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar {
                    if(it == MainAct.MenuButtonPrs)
                        uiScope.launch { drawerState.open() }
                    else onAction(it)
                }
            },
        ) { pd ->
            DashboardPage(
                Modifier
                    .fillMaxSize()
                    .padding(pd),
                dashboardSt, onAction
            )
        }
    }

    val context = LocalContext.current
    LaunchedEffect(mainSt.showToast?.id) {
        mainSt.showToast?.make(context)
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
//    val x = Note("Hello", "May name is billa")
    RtspStreamerTheme {
        MainPage(
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
        MainPage(
//            fragSt = NotesList(
//                notesList = persistentListOf(x),
//                notesSelected = persistentSetOf(x.id)
//            )
        )
    }
}