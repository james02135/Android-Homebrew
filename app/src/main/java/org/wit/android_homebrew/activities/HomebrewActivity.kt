package org.wit.android_homebrew.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_homebrew.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.android_homebrew.R
import org.wit.android_homebrew.models.HomebrewModel

class HomebrewActivity : AppCompatActivity(), AnkoLogger {

    var homebrew = HomebrewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homebrew)

        btnAdd.setOnClickListener() {
            homebrew.title = homebrewTitle.text.toString()
            if (homebrew.title.isNotEmpty()) {
                info("add Homebrew Button pressed: $homebrew")
            }
            else {
                toast ("Please enter a Homebrew Name")
            }
            info("add Button Pressed")
        }
    }
}