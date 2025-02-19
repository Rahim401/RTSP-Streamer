package com.s2bytes.rtspstreamer.main

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.s2bytes.rtspstreamer.application.MediaCallback
import com.s2bytes.rtspstreamer.application.MediaManager
import com.s2bytes.rtspstreamer.application.MediaManager.pause
import com.s2bytes.rtspstreamer.application.MediaManager.play
import com.s2bytes.rtspstreamer.application.MediaManager.playRtsp
import com.s2bytes.rtspstreamer.application.MediaManager.stateNow
import com.s2bytes.rtspstreamer.application.MediaState
import com.s2bytes.rtspstreamer.application.Storage
import com.s2bytes.rtspstreamer.goToLink
import com.s2bytes.rtspstreamer.ui.pages.DashboardAct
import com.s2bytes.rtspstreamer.ui.pages.DashboardStates
import com.s2bytes.rtspstreamer.ui.pages.DrawerAct
import com.s2bytes.rtspstreamer.ui.pages.DrawerStates
import com.s2bytes.rtspstreamer.ui.pages.MainAct
import com.s2bytes.rtspstreamer.ui.pages.MainStates
import com.s2bytes.rtspstreamer.ui.pages.ToastMessage
import com.s2bytes.rtspstreamer.ui.pages.UiAction
import org.videolan.libvlc.MediaPlayer

const val devName = "Rahim H"
const val devGithubInLink = "https://github.com/rahim401"
const val devLinkedInLink = "https://www.linkedin.com/in/hrahim401/"
const val devTwitterInLink = "https://x.com"

class MainVM: ViewModel() {
    private val mediaCallback = object: MediaCallback {
        private var isPrevIdle = false
        override fun onStateChange(from: MediaState?, to: MediaState?) {
            playbackState = to ?: MediaState.Uninitialized
            if(from == MediaState.Connecting && to == MediaState.Idle)
                toastToMake = ToastMessage("Stream Unavailable!")
            if(isPrevIdle && from == MediaState.Connecting && to == MediaState.Playing) {
                Storage.noPrevStreams = noOfPrevStreams++
            }
            isPrevIdle = from == MediaState.Idle
//            println("$from -> $to")
        }
        override fun onEvent(event: MediaPlayer.Event) {}
    }
    fun initializeModel(context: Context) {
        MediaManager.configure(context, isForcedToUseTcp, isOptimizedForLive)
        MediaManager.addCallback(mediaCallback)
    }

    private var toastToMake by mutableStateOf<ToastMessage?>(null)
    private var playbackState by mutableStateOf(MediaState.Uninitialized)
    private var currentVideoLink by mutableStateOf(Storage.lastLink)
    private fun handelDashboardAction(action: DashboardAct) {
        when(action) {
            is DashboardAct.PlayFromUrl -> {
                currentVideoLink = action.url
                Storage.lastLink = currentVideoLink
                MediaManager.stop()
                MediaManager.playRtsp(currentVideoLink)
            }
            is DashboardAct.VideoViewCreated -> MediaManager.attachView(action.view)
            DashboardAct.VideoViewDestroyed -> MediaManager.detachViews()
            DashboardAct.PauseOrPlayedVideo -> when(stateNow) {
                MediaState.Playing -> pause()
                MediaState.Paused -> play()
                MediaState.Idle -> playRtsp(currentVideoLink)
                else -> {}
            }
            DashboardAct.StopVideo -> MediaManager.stop()
        }
    }

    private var noOfPrevStreams by mutableIntStateOf(Storage.noPrevStreams)
    private var isForcedToUseTcp by mutableStateOf(Storage.useTcp)
    private var isOptimizedForLive by mutableStateOf(Storage.forLive)
    private fun handelDrawerAction(action: DrawerAct, context: Context? = null) {
        when(action) {
            is DrawerAct.NoOfStreamPrs -> {}
            is DrawerAct.MediaPrs -> {
                when(action.media) {
                    "LinkedIn" -> context?.goToLink(devLinkedInLink)
                    "Twitter" -> context?.goToLink(devTwitterInLink)
                    "Github" -> context?.goToLink(devGithubInLink)
                    else -> {}
                }
            }
            is DrawerAct.ForceUsingTCP -> {
                isForcedToUseTcp = action.isEnabled; Storage.useTcp = isForcedToUseTcp
                context?.let { MediaManager.configure(it, isForcedToUseTcp, isOptimizedForLive) }
            }
            is DrawerAct.OptimizeForLive -> {
                isOptimizedForLive = action.isEnabled; Storage.forLive = isOptimizedForLive
                context?.let { MediaManager.configure(it, isForcedToUseTcp, isOptimizedForLive) }
            }
        }
    }

    fun handelAction(action: UiAction, context: Context? = null) {
//        println("Handling action $action")
        when(action) {
            is MainAct -> {}
            is DrawerAct -> handelDrawerAction(action, context)
            is DashboardAct -> handelDashboardAction(action)
        }
    }

    override fun onCleared() {
        super.onCleared()
        MediaManager.removeCallback(mediaCallback)
        MediaManager.release()
    }

    fun getMainStates() = MainStates(toastToMake)
    fun getDrawerStates() = DrawerStates(devName, noOfPrevStreams, isForcedToUseTcp, isOptimizedForLive)
    fun getDashboardStates() = DashboardStates(currentVideoLink, playbackState)
}