package org.wit.android_homebrew.activities


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        var edit = false
        info("Homebrew Activity started")

        app = application as MainApp

        if (intent.hasExtra("homebrew_edit")) {
            edit = true
            homebrew = intent.extras?.getParcelable<HomebrewModel>("homebrew_edit")!!
            homebrewName.setText(homebrew.name)
            homebrewStyle.setText(homebrew.style)
            btnAdd.setText(R.string.save_homebrew)
        }

        btnAdd.setOnClickListener() {
            homebrew.name = homebrewName.text.toString()
            homebrew.style = homebrewStyle.text.toString()

            if (homebrew.name.isEmpty()) {
                toast(R.string.enter_homebrew_name)
            } else {
                if (edit) {
                    app.homebrews.update(homebrew.copy())
                } else {
                    app.homebrews.create(homebrew.copy())
                }
            }
            info("add Homebrew Button pressed: $homebrew")
            setResult(AppCompatActivity.RESULT_OK)
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