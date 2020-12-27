package org.wit.android_homebrew.models

interface HomebrewStore {
    fun findAll(): List<HomebrewModel>
    fun create(homebrew: HomebrewModel)
    fun update(homebrew: HomebrewModel)
    fun delete(homebrew: HomebrewModel)
}