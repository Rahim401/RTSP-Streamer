package com.s2bytes.rtspstreamer.ui.pages

sealed class UiAction

data class MainStates(
    val isDrawerOpen: Boolean = false
)
sealed class MainAct: UiAction() {
    data object MenuButtonPrs: MainAct()
    data object MenuDrawerDismissed: MainAct()
}

data class DrawerStates(
    val userName: String = "Rahim H",
    val noOfThisStreams: Int = 0,
    val isStreamingWithTCP: Boolean = false
)
sealed class DrawerAct: UiAction() {
    data class MediaPrs(val media: String): DrawerAct()
    data object NoOfStreamPrs: DrawerAct()
    data class ForceUsingTCP(val isEnabled: Boolean) : DrawerAct()
}

data class DashboardStates(
    val summa: Boolean = false
)
sealed class DashboardAct: UiAction() {
//    data object MenuButtonPrs: MainAct()
//    data object MenuDrawerDismissed: MainAct()
}