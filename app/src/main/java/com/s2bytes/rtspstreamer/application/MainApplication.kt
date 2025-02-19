package com.s2bytes.rtspstreamer.application

import android.app.Application

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Storage.initialize(this)
    }
}