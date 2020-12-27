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
        val malts : MutableList<String> = mutableListOf(),
        var malt : String = "",
        val hops : MutableList<String> = mutableListOf(),
        var hop : String = "",
        var yeast : String = "",
        var targetOG: Double = 0.0,
        var targetFG: Double = 0.0,
        var actualOG : Double = 0.0,
        var actualFG : Double = 0.0,
        var FermTime: Int = 0,


        //var anotherMalt : Boolean = false,
        //var nextMalt : Int = 0,
        //var maltChoice : String = "",
        //var anotherHop : Boolean = false,
        //var nextHop : Int = 0,
        //var hopChoice : String = "",
        //var hopTime1 : Int = 0,
        //val hopTimes : MutableList<Int> = mutableListOf(),
        //var dryHop : String = "",
        //var dryHopType : String = "",
        //var dryHopLength : Int = 0,
        //var primaryFermTime : Int = 0,
        //var secondaryFermTime : Int = 0,

) : Parcelable