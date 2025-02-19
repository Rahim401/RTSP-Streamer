package com.s2bytes.rtspstreamer.ui.pages

import android.content.Context
import com.s2bytes.rtspstreamer.application.MediaState
import com.s2bytes.rtspstreamer.makeToast
import org.videolan.libvlc.util.VLCVideoLayout

sealed class UiAction

data class ToastMessage(val message: String) {
    val id: Int = getCount()
    private var isDone = false
    fun make(context: Context) {
        if(!isDone) {
            context.makeToast(message)
            isDone = true
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is ToastMessage && other.id == id
    }

    override fun hashCode(): Int {
        return id
    }

    companion object {
        private var idCount = 0
        private fun getCount(): Int {
            return idCount++
        }
    }
}
data class MainStates(
    val showToast: ToastMessage? = null
)
sealed class MainAct: UiAction() {
    data object MenuButtonPrs: MainAct()
    data object MenuDrawerDismissed: MainAct()
}

data class DrawerStates(
    val userName: String = "Rahim H",
    val noOfThisStreams: Int = 0,
    val isStreamingWithTCP: Boolean = false,
    val isOptimizedForLive: Boolean = true
)
sealed class DrawerAct: UiAction() {
    data class MediaPrs(val media: String): DrawerAct()
    data object NoOfStreamPrs: DrawerAct()
    data class ForceUsingTCP(val isEnabled: Boolean) : DrawerAct()
    data class OptimizeForLive(val isEnabled: Boolean) : DrawerAct()
}

data class DashboardStates(
    val defVideoLink: String = "",
    val playbackState: MediaState = MediaState.Uninitialized
)
sealed class DashboardAct: UiAction() {
    data class PlayFromUrl(val url: String): DashboardAct()
    data class VideoViewCreated(val view: VLCVideoLayout): DashboardAct()
    data object VideoViewDestroyed: DashboardAct()
    data object PauseOrPlayedVideo: DashboardAct()
    data object StopVideo: DashboardAct()
//    data object MenuButtonPrs: MainAct()
//    data object MenuDrawerDismissed: MainAct()
}