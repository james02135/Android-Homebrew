package org.wit.android_homebrew.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.android_homebrew.helpers.*
import java.util.*

const val JSON_FILE = "homebrews.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<HomebrewModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class HomebrewJSONStore(context: Context) : HomebrewStore, AnkoLogger {

    private val context: Context
    var homebrews = mutableListOf<HomebrewModel>()

    init {
        context.also { this.context = it }
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<HomebrewModel> {
        return homebrews
    }

    override fun create(homebrew: HomebrewModel) {
        homebrew.id = generateRandomId()
        homebrews.add(homebrew)
        serialize()
    }

    override fun update(homebrew: HomebrewModel) {
        val homebrewList = findAll() as ArrayList<HomebrewModel>
        val foundHomebrew: HomebrewModel? = homebrewList.find { p -> p.id == homebrew.id }
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
        }
        serialize()
    }

    override fun delete(homebrew: HomebrewModel) {
        homebrews.remove(homebrew)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(homebrews, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        homebrews = Gson().fromJson(jsonString, listType)
    }
}