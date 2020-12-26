package org.wit.android_homebrew.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_homebrew.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.android_homebrew.R
import org.wit.android_homebrew.models.HomebrewModel

class HomebrewActivity : AppCompatActivity(), AnkoLogger {

    var homebrew = HomebrewModel()
    var homebrews = ArrayList<HomebrewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homebrew)

        btnAdd.setOnClickListener() {
            homebrew.name = homebrewName.text.toString()
            homebrew.description = description.text.toString()
            if (homebrew.name.isNotEmpty()) {
                homebrews.add(homebrew.copy())
                info("add Homebrew Button pressed: $homebrew")
                for (i in homebrews.indices) {
                    info("Homebrew[$i]:${this.homebrews[i]}")
                }
            }
            else {
                toast ("Please enter a Homebrew Name")
            }
        }
    }
}