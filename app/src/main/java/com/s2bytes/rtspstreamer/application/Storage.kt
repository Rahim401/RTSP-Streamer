package com.s2bytes.rtspstreamer.application

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object Storage {
    private lateinit var preference: SharedPreferences
    var lastLink: String
        get() = preference.getString("lastLink", "") ?: ""
        set(value) = preference.edit(true) { putString("lastLink", value) }
    var useTcp: Boolean
        get() = preference.getBoolean("useTcp", false)
        set(value) = preference.edit(true) { putBoolean("useTcp", value) }
    var forLive: Boolean
        get() = preference.getBoolean("forLive", true)
        set(value) = preference.edit(true) { putBoolean("forLive", value) }

    fun initialize(context: Context) {
        preference = context.getSharedPreferences("Storage", Context.MODE_PRIVATE)
    }
}