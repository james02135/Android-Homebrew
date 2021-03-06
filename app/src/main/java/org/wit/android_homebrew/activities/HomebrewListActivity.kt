package org.wit.android_homebrew.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_homebrew_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.android_homebrew.R
import org.wit.android_homebrew.main.MainApp
import org.wit.android_homebrew.models.HomebrewModel

/*
@author James Richardson

HomebrewListActivity governs how the list of Homebrews functions
 */


class HomebrewListActivity : AppCompatActivity(), HomebrewListener {

    private lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homebrew_list)
        app = application as MainApp

        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadHomebrews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<HomebrewActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onHomebrewClick(homebrew: HomebrewModel) {
        startActivityForResult(intentFor<HomebrewActivity>().putExtra("homebrew_edit", homebrew), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadHomebrews()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadHomebrews() {
        showHomebrews(app.homebrews.findAll())
    }

    private fun showHomebrews (homebrews: List<HomebrewModel>) {
        recyclerView.adapter = HomebrewAdapter(homebrews, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

