package org.wit.android_homebrew.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.android_homebrew.models.HomebrewMemStore

class MainApp : Application(), AnkoLogger {

    val homebrews = HomebrewMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Homebrew App started")
    }
}