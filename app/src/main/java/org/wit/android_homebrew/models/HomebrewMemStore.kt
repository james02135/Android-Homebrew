package org.wit.android_homebrew.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class HomebrewMemStore : HomebrewStore, AnkoLogger {

    private val homebrews = ArrayList<HomebrewModel>()

    override fun findAll(): List<HomebrewModel> {
        return homebrews
    }

    override fun create(homebrew: HomebrewModel) {
        homebrew.id = getId()
        homebrews.add(homebrew)
        logAll()
    }

    override fun update(homebrew: HomebrewModel) {
        val foundHomebrew: HomebrewModel? = homebrews.find { p -> p.id == homebrew.id }
        if (foundHomebrew != null) {
            foundHomebrew.name = homebrew.name
            foundHomebrew.style = homebrew.style
            foundHomebrew.brewDate = homebrew.brewDate
            foundHomebrew.ABV = homebrew.ABV
            foundHomebrew.boilLength = homebrew.boilLength
            foundHomebrew.hop1 = homebrew.hop1
            foundHomebrew.malt1 = homebrew.malt1
            foundHomebrew.yeast = homebrew.yeast
            foundHomebrew.targetOG = homebrew.targetOG
            foundHomebrew.targetFG = homebrew.targetFG
            foundHomebrew.actualOG = homebrew.actualOG
            foundHomebrew.actualFG = homebrew.actualFG
            foundHomebrew.fermTime = homebrew.fermTime

            logAll()
        }
    }

    override fun delete(homebrew: HomebrewModel) {
        homebrew.id = getId()
        homebrews.remove(homebrew)
        logAll()
    }


    private fun logAll() {
        homebrews.forEach{ info("$it") }
    }
}