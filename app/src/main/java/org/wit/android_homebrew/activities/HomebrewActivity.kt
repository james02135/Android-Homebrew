package org.wit.android_homebrew.activities


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_homebrew.*
import kotlinx.android.synthetic.main.activity_homebrew.homebrewName
import kotlinx.android.synthetic.main.activity_homebrew.homebrewStyle
import kotlinx.android.synthetic.main.card_homebrew.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.android_homebrew.R
import org.wit.android_homebrew.main.MainApp
import org.wit.android_homebrew.models.HomebrewMemStore
import org.wit.android_homebrew.models.HomebrewModel
import org.wit.android_homebrew.models.HomebrewStore
import java.nio.file.Files.delete


class HomebrewActivity : AppCompatActivity(), AnkoLogger {

    var homebrew = HomebrewModel()
    lateinit var app : MainApp
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {//Either create a new homebrew, or edit an existing homebrew
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homebrew)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        info("Homebrew Activity started")

        app = application as MainApp

        if (intent.hasExtra("homebrew_edit")) {//If the intent is to edit the Homebrew Name or Style
            edit = true
            homebrew = intent.extras?.getParcelable<HomebrewModel>("homebrew_edit")!!

            //Setting the strings
            homebrewName.setText(homebrew.name)
            homebrewStyle.setText(homebrew.style)
            homebrewHop.setText(homebrew.hop)
            homebrewMalt.setText(homebrew.malt)
            homebrewYeast.setText(homebrew.yeast)
            homebrewDate.setText(homebrew.brewDate)

            //Setting the ints
            var boil = homebrew.boilLength.toString()
            homebrewBoil.setText(boil)
            var fermTime = homebrew.fermTime.toString()
            homebrewFT.setText(fermTime)


            //Setting the doubles
            var abv = homebrew.ABV.toString()
            homebrewABV.setText(abv)
            var tog = homebrew.targetOG.toString()
            homebrewTOG.setText(tog)
            var tfg = homebrew.targetFG.toString()
            homebrewTFG.setText(tfg)
            var aog = homebrew.actualOG.toString()
            homebrewAOG.setText(aog)
            var afg = homebrew.actualFG.toString()
            homebrewAFG.setText(afg)

            //Setting the add button
            btnAdd.setText(R.string.save_homebrew)
        }//if intent is to edit

        btnAdd.setOnClickListener() {//Adding a new homebrew
            homebrew.name = homebrewName.text.toString()
            homebrew.style = homebrewStyle.text.toString()
            homebrew.hop = homebrewHop.text.toString()
            homebrew.malt = homebrewMalt.text.toString()
            homebrew.yeast = homebrewYeast.text.toString()
            homebrew.brewDate = homebrewDate.text.toString()
            homebrew.boilLength = homebrewBoil.text.toString().toInt()
            homebrew.fermTime = homebrewFT.text.toString().toInt()
            homebrew.ABV = homebrewABV.text.toString().toDouble()
            homebrew.targetOG = homebrewTOG.text.toString().toDouble()
            homebrew.targetFG = homebrewTFG.text.toString().toDouble()
            homebrew.actualOG = homebrewAOG.text.toString().toDouble()
            homebrew.actualFG = homebrewAFG.text.toString().toDouble()
            if (homebrew.name.isEmpty()) {
                toast(R.string.enter_homebrew_name)
            } else {
                if (edit) {
                    app.homebrews.update(homebrew.copy())
                } else {
                    app.homebrews.create(homebrew.copy())
                }
            }
            info("add Homebrew Button pressed: $homebrewName")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        btnDel.setOnClickListener() {
            app.homebrews.delete(homebrew)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}