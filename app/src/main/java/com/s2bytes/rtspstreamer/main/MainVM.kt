package com.s2bytes.rtspstreamer.main

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.s2bytes.rtspstreamer.application.MediaCallback
import com.s2bytes.rtspstreamer.application.MediaManager
import com.s2bytes.rtspstreamer.application.MediaState
import com.s2bytes.rtspstreamer.application.Storage
import com.s2bytes.rtspstreamer.goToLink
import com.s2bytes.rtspstreamer.ui.pages.DashboardAct
import com.s2bytes.rtspstreamer.ui.pages.DashboardStates
import com.s2bytes.rtspstreamer.ui.pages.DrawerAct
import com.s2bytes.rtspstreamer.ui.pages.DrawerStates
import com.s2bytes.rtspstreamer.ui.pages.MainAct
import com.s2bytes.rtspstreamer.ui.pages.MainStates
import com.s2bytes.rtspstreamer.ui.pages.UiAction
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCUtil

const val devName = "Rahim H"
const val devGithubInLink = "https://x.com/acharya_ac_in"
const val devLinkedInLink = "https://www.linkedin.com/school/acharya-institutes"
const val devTwitterInLink = "https://x.com/acharya_ac_in"

class MainVM: ViewModel() {
    private val mediaCallback = object: MediaCallback {
        override fun onStateChange(from: MediaState?, to: MediaState?) {
            println("$from -> $to")
            playbackState = to ?: MediaState.Uninitialized
        }

        override fun onEvent(event: MediaPlayer.Event) {
//            println("${event.type.toHexString().substring(5)} ${event.timeChanged}")
        }
    }
    fun initializeModel(context: Context) {
        MediaManager.configure(context, isForcedToUseTcp, isOptimizedForLive)
        MediaManager.addCallback(mediaCallback)
    }

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
            DashboardAct.PauseOrPlayedVideo -> MediaManager.pauseOrPlay()
            DashboardAct.StopVideo -> MediaManager.stop()
        }
    }

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
        println("Handling action $action")
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

    fun getMainStates() = MainStates()
    fun getDrawerStates() = DrawerStates(devName, 0, isForcedToUseTcp, isOptimizedForLive)
    fun getDashboardStates() = DashboardStates(currentVideoLink, playbackState)
}