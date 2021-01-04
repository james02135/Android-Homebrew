package org.wit.android_homebrew.activities


import android.content.Intent
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
import org.wit.android_homebrew.helpers.readImage
import org.wit.android_homebrew.helpers.readImageFromPath
import org.wit.android_homebrew.helpers.showImagePicker
import org.wit.android_homebrew.main.MainApp
import org.wit.android_homebrew.models.HomebrewModel

/*
@author James Richardson

Class HomebrewActivity controls how a new Homebrew is added, and edited
 */


class HomebrewActivity : AppCompatActivity(), AnkoLogger {

    private var homebrew = HomebrewModel()
    private lateinit var app : MainApp
    private var edit = false
    private val imageRequest = 1

    //Array of beer styles
    private var styleList = arrayOf("- Style -", "None", "Pale Ale", "IPA", "BIPA", "NEIPA", "DIPA", "Barleywine", "Blonde Ale", "White Ale", "Red Ale",
                                    "Milk Stout", "Dry Stout", "Sweet Stout", "Imperial Stout", "Porter", "Baltic Porter", "Imperial Porter",
                                    "Pale Lager", "IPL", "Hefeweizen", "Dunkelweizen", "Vienna Lager", "Pilsner", "Doppelbock", "Belgian Ale",
                                    "Belgian Dubbel", "Belgian Tripel", "Belgian Quad")

    //Array of malt varieties
    private var maltList = arrayOf("- Malts -", "None", "Pale", "Maris Otter", "Black", "Brown", "Chocolate", "DME Extra Light", "DME Light", "DME Amber", "DME Dark",
                                    "LME Extra Light", "LME Light", "LME Amber", "LME Dark", "Smoked", "Caramel", "Wheat", "Munich", "Pilsen", "Red",
                                    "Rye")

    //Array of hop varieties
    private var hopList = arrayOf("- Hops -", "None", "Admiral", "Ahtanum", "Amarillo", "Apollo", "Archer", "Azacca", "Bramling Cross", "Bravo", "Cascade",
                                    "Centennial", "Challenger", "Chinook", "Citra", "Columbus", "Comet", "Crystal", "East Kent Goldings", "El Dorado",
                                    "Ella", "First Gold", "Fuggles", "Galaxy", "Hallertauer", "Idaho-7", "Mosaic", "Nugget", "Saaz", "Simcoe", "Warrior" )

    //Array of choices whether the user Dry Hopped their homebrew
    private var hopChoice = arrayOf("Did You Dry Hop?", "Yes", "No")


    //Either create a new homebrew, or edit an existing homebrew
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homebrew)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        //set Style spinner with adapter
        val styleAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, styleList)
        homebrewStyles.adapter = styleAdapter

        //set Malt spinners with adapter
        val maltAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, maltList)
        homebrewFirstMalt.adapter = maltAdapter
        homebrewSecondMalt.adapter = maltAdapter
        homebrewThirdMalt.adapter = maltAdapter

        //set Hop spinners with adapter
        val hopAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, hopList)
        homebrewFirstHop.adapter = hopAdapter
        homebrewSecondHop.adapter = hopAdapter
        homebrewThirdHop.adapter = hopAdapter
        homebrewDryHopVariety.adapter = hopAdapter

        //set Dry Hop Choice spinner with adapter
        val hopChoiceAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, hopChoice)
        homebrewDryHopChoice.adapter = hopChoiceAdapter


        info("Homebrew Activity started")
            app = application as MainApp

            if (intent.hasExtra("homebrew_edit")) {//If the intent is to edit the Homebrew Name or Style
                edit = true
                homebrew = intent.extras?.getParcelable("homebrew_edit")!!
                homebrewImage.setImageBitmap(readImageFromPath(this, homebrew.image))

                //Setting the strings
                homebrewName.setText(homebrew.name)
                homebrewYeast.setText(homebrew.yeast)
                homebrewDate.setText(homebrew.brewDate)
                homebrewNotes.setText(homebrew.notes)

                //Setting the spinners
                homebrewStyles.setSelection(styleList.indexOf(homebrew.style))
                homebrewFirstMalt.setSelection(maltList.indexOf(homebrew.malt1))
                homebrewSecondMalt.setSelection(maltList.indexOf(homebrew.malt2))
                homebrewThirdMalt.setSelection(maltList.indexOf(homebrew.malt3))
                homebrewFirstHop.setSelection(hopList.indexOf(homebrew.hop1))
                homebrewSecondHop.setSelection(hopList.indexOf(homebrew.hop2))
                homebrewThirdHop.setSelection(hopList.indexOf(homebrew.hop3))
                homebrewDryHopVariety.setSelection(hopList.indexOf(homebrew.dryHopVariety))
                homebrewDryHopChoice.setSelection(hopChoice.indexOf(homebrew.dryHopChoice))

                //Setting the ints
                val boil = homebrew.boilLength.toString()
                homebrewBoil.setText(boil)
                val fermTime = homebrew.fermTime.toString()
                homebrewFT.setText(fermTime)
                val dryHopLength = homebrew.dryHopLength.toString()
                homebrewDryHopLength.setText(dryHopLength)


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
                //Make the delete button visible
                btnDel.setTransitionVisibility(View.VISIBLE)
                btnDel.setOnClickListener {
                    app.homebrews.delete(homebrew)
                    finish()
                }

            }

            //Adding a new homebrew
            btnAdd.setOnClickListener {
                //Setting the strings
                homebrew.name = homebrewName.text.toString()
                homebrew.style = homebrewStyles.selectedItem.toString()
                homebrew.malt1 = homebrewFirstMalt.selectedItem.toString()
                homebrew.malt2 = homebrewSecondMalt.selectedItem.toString()
                homebrew.malt3 = homebrewThirdMalt.selectedItem.toString()
                homebrew.hop1 = homebrewFirstHop.selectedItem.toString()
                homebrew.hop2 = homebrewSecondHop.selectedItem.toString()
                homebrew.hop3 = homebrewThirdHop.selectedItem.toString()
                homebrew.yeast = homebrewYeast.text.toString()
                homebrew.brewDate = homebrewDate.text.toString()
                homebrew.notes = homebrewNotes.text.toString()
                homebrew.dryHopChoice = homebrewDryHopChoice.selectedItem.toString()
                //Setting the ints
                homebrew.boilLength = homebrewBoil.text.toString().toInt()
                homebrew.fermTime = homebrewFT.text.toString().toInt()
                //Setting the doubles
                homebrew.ABV = homebrewABV.text.toString().toDouble()
                homebrew.targetOG = homebrewTOG.text.toString().toDouble()
                homebrew.targetFG = homebrewTFG.text.toString().toDouble()
                homebrew.actualOG = homebrewAOG.text.toString().toDouble()
                homebrew.actualFG = homebrewAFG.text.toString().toDouble()
                //If statement to allow the user to enter Dry Hopping information if applicable
                if (homebrewDryHopChoice.equals("Yes")) {
                    homebrew.dryHopLength = homebrewDryHopLength.text.toString().toInt()
                    homebrew.dryHopVariety = homebrewDryHopVariety.selectedItem.toString()
                }
                //Validation if the name is empty
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
        //Setting the Select Image button
        btnAddImage.setOnClickListener {
            showImagePicker(this, imageRequest)
            info ("Select image")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            imageRequest -> {
                if (data != null) {
                    homebrew.image = data.data.toString()
                    homebrewImage.setImageBitmap(readImage(this, resultCode, data))
                }
            }
        }
    }
}