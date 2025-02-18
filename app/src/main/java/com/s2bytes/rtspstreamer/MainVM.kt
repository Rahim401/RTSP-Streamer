package com.s2bytes.rtspstreamer

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.s2bytes.rtspstreamer.ui.pages.DashboardAct
import com.s2bytes.rtspstreamer.ui.pages.DashboardStates
import com.s2bytes.rtspstreamer.ui.pages.DrawerAct
import com.s2bytes.rtspstreamer.ui.pages.DrawerStates
import com.s2bytes.rtspstreamer.ui.pages.MainAct
import com.s2bytes.rtspstreamer.ui.pages.MainStates
import com.s2bytes.rtspstreamer.ui.pages.UiAction
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCVideoLayout
import java.lang.ref.WeakReference

const val devName = "Rahim H"
const val devGithubInLink = "https://x.com/acharya_ac_in"
const val devLinkedInLink = "https://www.linkedin.com/school/acharya-institutes"
const val devTwitterInLink = "https://x.com/acharya_ac_in"

class MainVM: ViewModel() {
    private lateinit var libVLC: LibVLC
    private lateinit var mediaPlayer: MediaPlayer
    @OptIn(ExperimentalStdlibApi::class)
    private var mediaEventListener = MediaPlayer.EventListener { event ->
        println("${event.type.toHexString().substring(5)} ${event.pausable} ${event.seekable} ${event.timeChanged}")
    }

    fun initializeModel(context: Context) {
        val option = mutableListOf(
            "--rtsp-tcp", "--live-caching=0", "--file-caching=0", "--drop-late-frames",
            "--skip-frames", "--disc-caching=3000", "--sout-mux-caching=0",
        )
        libVLC = LibVLC(context, option)
        mediaPlayer = MediaPlayer(libVLC)
        mediaPlayer.setEventListener(mediaEventListener)
    }

//    private var currentMedia: Media? = null
    private var mVideoLayout: WeakReference<VLCVideoLayout>? = null
    private var currentVideoLink by mutableStateOf("")
    private fun handelDashboardAction(action: DashboardAct) {
        when(action) {
            is DashboardAct.PlayFromUrl -> {
                currentVideoLink = action.url
                if(mediaPlayer.isPlaying) mediaPlayer.stop()
                val uri = Uri.parse(currentVideoLink)
                val media = Media(libVLC, uri).apply {
                    setHWDecoderEnabled(true, false)
                    addOption(":network-caching=300")
                    addOption(":clock-jitter=0")
                    addOption(":clock-synchro=0")
                    addOption(":rtsp-caching=0")
                }
                mediaPlayer.play(media)
            }
            is DashboardAct.VideoViewCreated -> {
                mVideoLayout = WeakReference(action.view)
                mediaPlayer.attachViews(action.view, null, false, false)
            }
            DashboardAct.VideoViewDestroyed -> {
                mediaPlayer.detachViews()
                mVideoLayout?.clear()
                mVideoLayout = null
            }
        }
    }

    private var isForcedToUseTcp by mutableStateOf(false)
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
                isForcedToUseTcp = action.isEnabled
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
        mediaPlayer.release()
        libVLC.release()
    }

    fun getMainStates() = MainStates()
    fun getDrawerStates() = DrawerStates(devName, isStreamingWithTCP = isForcedToUseTcp)
    fun getDashboardStates() = DashboardStates(currentVideoLink)
}