package org.wit.android_homebrew.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.android_homebrew.models.HomebrewJSONStore
import org.wit.android_homebrew.models.HomebrewMemStore
import org.wit.android_homebrew.models.HomebrewStore

class MainApp : Application(), AnkoLogger {

    lateinit var homebrews: HomebrewStore

    override fun onCreate() {
        super.onCreate()
        homebrews = HomebrewJSONStore(applicationContext)
        info("Homebrew App started")
    }
}