package com.s2bytes.rtspstreamer.application

import android.content.Context
import android.net.Uri
import com.s2bytes.rtspstreamer.add
import com.s2bytes.rtspstreamer.addOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.MediaPlayer.Event
import org.videolan.libvlc.util.VLCVideoLayout
import java.lang.ref.WeakReference

enum class MediaState {
    Uninitialized, Idle,
    Connecting, Playing,
    Paused
}

interface MediaCallback {
    fun onStateChange(from: MediaState?, to: MediaState?)
    fun onEvent(event: Event)
}

object MediaManager {
    private lateinit var vlcLib: LibVLC
    private lateinit var vlcPlayer: MediaPlayer
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val stateChangeCBs = mutableListOf<MediaCallback>()
    private val mediaEventCallback = MediaPlayer.EventListener { event ->
        when(event.type) {
            Event.Opening -> stateNow = MediaState.Connecting
            Event.Playing -> stateNow = MediaState.Playing
            Event.Paused -> stateNow = MediaState.Paused
            Event.Stopped -> stateNow = MediaState.Idle
            Event.EndReached -> {
                retryConnecting = false
                stateNow = MediaState.Idle
            }
        }
        uiScope.launch { stateChangeCBs.forEach { it.onEvent(event) } }
    }
    private var videoLayout: WeakReference<VLCVideoLayout>? = null

    private var useTcpToStream: Boolean = false;
    private var optimizeForLive: Boolean = true;
    private var lastPlayedLink: String? = null
    private var retryConnecting = false
    var stateNow: MediaState = MediaState.Uninitialized
        private set(value) {
            if(value == field) return; val old = field; field = value
            uiScope.launch { stateChangeCBs.forEach { it.onStateChange(old, value) } }
        }

    fun configure(context: Context, useTcp: Boolean = false, forLive: Boolean = true) {
        if(stateNow != MediaState.Uninitialized && useTcpToStream == useTcp && forLive == optimizeForLive)
            return

        release()
        useTcpToStream = useTcp; optimizeForLive = forLive
        val libOptions = mutableListOf("--file-caching=0", "--disc-caching=3000", "--sout-mux-caching=0")
        if(useTcpToStream) libOptions.add("--rtsp-tcp")
        if(!optimizeForLive) libOptions.add("--live-caching=300", )
        else libOptions.add("--live-caching=0", "--drop-late-frames", "--skip-frames")

        vlcLib = LibVLC(context, libOptions)
        vlcPlayer = MediaPlayer(vlcLib)
        stateNow = MediaState.Idle
        vlcPlayer.setEventListener(mediaEventCallback)
        tryToPlay()
    }
    fun attachView(view: VLCVideoLayout) = ifReady {
        vlcPlayer.attachViews(view, null, false, false)
        videoLayout = WeakReference(view)
        tryToPlay()
    }
    fun detachViews() = ifReady { vlcPlayer.detachViews() }

    fun addCallback(cb: MediaCallback) {
        stateChangeCBs.add(cb)
        uiScope.launch { cb.onStateChange(null, stateNow) }
    }
    fun removeCallback(cb: MediaCallback) {
        if(stateChangeCBs.remove(cb))
            uiScope.launch { cb.onStateChange(stateNow, null) }
    }

    private inline fun ifReady(block: () -> Unit) {
        if(stateNow != MediaState.Uninitialized) block()
    }
    private fun Media.configure(): Media {
        setHWDecoderEnabled(true, false)
        if(!optimizeForLive) addOptions(":network-caching=300", ":rtsp-caching=300", ":clock-synchro=0")
        else addOptions(":network-caching=0", ":rtsp-caching=0", ":clock-synchro=0", ":clock-jitter=0")
        return this
    }
    private fun tryToPlay() = ifReady {
        if(!retryConnecting) return@ifReady
        val link = lastPlayedLink ?: return@ifReady
        val uri = Uri.parse(link)
        val media = Media(vlcLib, uri).configure()
        if(!vlcPlayer.vlcVout.areViewsAttached()) videoLayout?.get()?.let {
            vlcPlayer.attachViews(
                it, null,
                false, false
            )
        }
        vlcPlayer.play(media)
        media.release()
    }

    fun playRtsp(link: String? = lastPlayedLink) = ifReady {
        if(vlcPlayer.isPlaying) stop()
        lastPlayedLink = link
        retryConnecting = true
        tryToPlay()
    }
    fun play() = ifReady { vlcPlayer.play() }
    fun pause() = ifReady { vlcPlayer.pause() }
    fun pauseOrPlay() = ifReady {
        when(stateNow) {
            MediaState.Playing -> pause()
            MediaState.Paused -> play()
            MediaState.Idle -> playRtsp()
            else -> {}
        }
    }
    fun stop() = ifReady {
        retryConnecting = false
        vlcPlayer.stop()
    }

    fun release() = ifReady {
        vlcPlayer.stop(); vlcPlayer.release()
        vlcLib.release()
        stateNow = MediaState.Idle
    }
}