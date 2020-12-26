package org.wit.android_homebrew.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.android_homebrew.models.HomebrewModel

class MainApp : Application(), AnkoLogger {

    var homebrews = ArrayList<HomebrewModel>()

    override fun onCreate() {
        super.onCreate()
        info("Homebrew App started")
    }
}