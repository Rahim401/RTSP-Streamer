package com.s2bytes.rtspstreamer

import android.content.Context
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

const val devName = "Rahim H"
const val devGithubInLink = "https://x.com/acharya_ac_in"
const val devLinkedInLink = "https://www.linkedin.com/school/acharya-institutes"
const val devTwitterInLink = "https://x.com/acharya_ac_in"

class MainVM: ViewModel() {
    fun initializeModel(context: Context) {}

    private var isForcedToUseTcp by mutableStateOf(false)
    fun handelAction(action: UiAction, context: Context? = null) {
        println("Handling action $action")
        when(action) {
            is MainAct -> {}
            is DrawerAct -> handelDrawerAction(action, context)
            is DashboardAct -> handelDashboardAction(action)
        }
    }

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

    private fun handelDashboardAction(action: DashboardAct) {
//        when(action) {
//
//            else -> {}
//        }
    }

    fun getMainStates() = MainStates()
    fun getDrawerStates() = DrawerStates(devName, isStreamingWithTCP = isForcedToUseTcp)
    fun getDashboardStates() = DashboardStates()
}