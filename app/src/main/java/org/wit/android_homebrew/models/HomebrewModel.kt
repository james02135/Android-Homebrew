package org.wit.android_homebrew.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomebrewModel(
        var id: Long? = 0,
        var name: String = "",
        var style: String = "",
        var ABV : Double = 0.0,
        var brewDate: String = "",
        var boilLength : Int = 0,
        var malt : String = "",
        var hop : String = "",
        var yeast : String = "",
        var targetOG: Double = 0.0,
        var targetFG: Double = 0.0,
        var actualOG : Double = 0.0,
        var actualFG : Double = 0.0,
        var fermTime: Int = 0,
) : Parcelable