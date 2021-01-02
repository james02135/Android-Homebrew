package org.wit.android_homebrew.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomebrewModel(
        var id: Long? = 0,
        var name: String = "",
        var style: String = "",
        var ABV: Double = 0.0,
        var brewDate: String = "",
        var boilLength: Int = 0,
        var malt1: String = "",
        var malt2: String = "",
        var malt3: String = "",
        var hop1: String = "",
        var hop2: String = "",
        var hop3: String = "",
        var yeast: String = "",
        var targetOG: Double = 0.0,
        var targetFG: Double = 0.0,
        var actualOG: Double = 0.0,
        var actualFG: Double = 0.0,
        var dryHopChoice: String = "",
        var dryHopLength: Int = 0,
        var dryHopVariety: String = "",
        var fermTime: Int = 0,
        var notes: String = "",
        var image: String = "",
) : Parcelable