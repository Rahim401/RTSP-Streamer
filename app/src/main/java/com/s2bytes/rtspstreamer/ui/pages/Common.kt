package com.s2bytes.rtspstreamer.ui.pages

import org.videolan.libvlc.util.VLCVideoLayout

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
    val defVideoLink: String = ""
)
sealed class DashboardAct: UiAction() {
    data class PlayFromUrl(val url: String): DashboardAct()
    data class VideoViewCreated(val view: VLCVideoLayout): DashboardAct()
    data object VideoViewDestroyed: DashboardAct()
//    data object MenuButtonPrs: MainAct()
//    data object MenuDrawerDismissed: MainAct()
}