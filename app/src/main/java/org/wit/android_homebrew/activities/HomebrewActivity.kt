package org.wit.android_homebrew.activities


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_homebrew.*
import kotlinx.android.synthetic.main.activity_homebrew.homebrewName
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.android_homebrew.R
import org.wit.android_homebrew.main.MainApp
import org.wit.android_homebrew.models.HomebrewModel




class HomebrewActivity : AppCompatActivity(), AnkoLogger {

    private var homebrew = HomebrewModel()
    private lateinit var app : MainApp
    private var edit = false
    private var styleList = arrayOf("Style", "Pale Ale", "IPA", "BIPA", "NEIPA", "DIPA", "Barleywine", "Blonde Ale", "White Ale", "Red Ale")


    override fun onCreate(savedInstanceState: Bundle?) {//Either create a new homebrew, or edit an existing homebrew
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homebrew)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        //set spinner with adapter
        val styleAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, styleList)
        homebrewStyles.adapter = styleAdapter
        info("Homebrew Activity started")
            app = application as MainApp

            if (intent.hasExtra("homebrew_edit")) {//If the intent is to edit the Homebrew Name or Style
                edit = true
                homebrew = intent.extras?.getParcelable("homebrew_edit")!!

                //Setting the strings
                homebrewName.setText(homebrew.name)
                homebrewHop.setText(homebrew.hop)
                homebrewMalt.setText(homebrew.malt)
                homebrewYeast.setText(homebrew.yeast)
                homebrewDate.setText(homebrew.brewDate)

                homebrewStyles.setSelection(styleList.indexOf(homebrew.style))

                //Setting the ints
                val boil = homebrew.boilLength.toString()
                homebrewBoil.setText(boil)
                val fermTime = homebrew.fermTime.toString()
                homebrewFT.setText(fermTime)


                //Setting the doubles
                val abv = homebrew.ABV.toString()
                homebrewABV.setText(abv)
                val tog = homebrew.targetOG.toString()
                homebrewTOG.setText(tog)
                val tfg = homebrew.targetFG.toString()
                homebrewTFG.setText(tfg)
                val aog = homebrew.actualOG.toString()
                homebrewAOG.setText(aog)
                val afg = homebrew.actualFG.toString()
                homebrewAFG.setText(afg)

                //Setting the Save button
                btnAdd.setText(R.string.save_homebrew)
                btnDel.setTransitionVisibility(View.VISIBLE)
                btnDel.setOnClickListener {
                    app.homebrews.delete(homebrew)
                    finish()
                }

            }

            btnAdd.setOnClickListener {//Adding a new homebrew
                homebrew.name = homebrewName.text.toString()
                homebrew.style = homebrewStyles.selectedItem.toString()
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
                setResult(RESULT_OK)
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