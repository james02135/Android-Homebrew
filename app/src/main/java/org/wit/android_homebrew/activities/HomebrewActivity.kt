package org.wit.android_homebrew.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_homebrew.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.android_homebrew.R
import org.wit.android_homebrew.main.MainApp
import org.wit.android_homebrew.models.HomebrewModel

class HomebrewActivity : AppCompatActivity(), AnkoLogger {

    var homebrew = HomebrewModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homebrew)
        app = application as MainApp

        btnAdd.setOnClickListener() {
            homebrew.name = homebrewName.text.toString()
            homebrew.style = homebrewStyle.text.toString()
            if (homebrew.name.isNotEmpty()) {
                app!!.homebrews.add(homebrew.copy())
                info("add Homebrew Button pressed: $homebrew")
                for (i in app!!.homebrews.indices) {
                    info("Homebrew[$i]:${app!!.homebrews[i]}")
                }
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast ("Please enter a Homebrew Name")
            }
        }
    }
}