package com.s2bytes.rtspstreamer.ui.pages.main


import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.s2bytes.rtspstreamer.ui.pages.DashboardStates
import com.s2bytes.rtspstreamer.ui.pages.DrawerStates
import com.s2bytes.rtspstreamer.ui.pages.MainAct
import com.s2bytes.rtspstreamer.ui.pages.MainStates
import com.s2bytes.rtspstreamer.ui.pages.UiAction
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
    val scrollState = rememberLazyListState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val isOnTop by remember { derivedStateOf { scrollState.firstVisibleItemIndex == 0 } }

//    val onListFrag = fragSt is NotesList
////    val inSelectionMode = remember((fragSt as? NotesList)?.notesList) {
//////        derivedStateOf {
////            fragSt is NotesList && fragSt.notesSelected.isNotEmpty()
//////        }
////    }
//    val inSelectionMode = fragSt is NotesList && fragSt.notesSelected.isNotEmpty()
//    val isCreatingNewNote = fragSt is NotesEdit && fragSt.title.isBlank()
//    val isInEditMode = fragSt is NotesEdit && fragSt.isInEditMode

    ModalNavigationDrawer(
        drawerContent = { DrawerSheet(drawerSt, onAction) },
        drawerState = drawerState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar {
                    if(it == MainAct.MenuButtonPrs) uiScope.launch {
                        drawerState.open()
                    }
                    else onAction(it)
                }
            },
        ) { pd ->
            println(pd)
//            if(fragSt is NotesList) NoteListPage(
//                Modifier
//                    .fillMaxSize()
//                    .padding(pd),
//                fragSt, scrollState, onAction
//            )
//            else if(fragSt is NotesEdit) NotesEditPage(
//                Modifier
//                    .fillMaxSize()
//                    .padding(pd), fragSt,
//                remember { isCreatingNewNote },
//                onAction
//            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Preview() {
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