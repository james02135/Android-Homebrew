package org.wit.android_homebrew.activities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_homebrew_list.*
import kotlinx.android.synthetic.main.card_homebrew.view.*
import org.jetbrains.anko.startActivityForResult
import org.wit.android_homebrew.R
import org.wit.android_homebrew.main.MainApp
import org.wit.android_homebrew.models.HomebrewModel

class HomebrewListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homebrew_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = HomebrewAdapter(app.homebrews)

        toolbar.title = title
        setSupportActionBar(toolbar)
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
}

class HomebrewAdapter constructor(private var homebrews: List<HomebrewModel>) :
        RecyclerView.Adapter<HomebrewAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomebrewAdapter.MainHolder {
        return MainHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.card_homebrew,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: HomebrewAdapter.MainHolder, position: Int) {
        val homebrew = homebrews[holder.adapterPosition]
        holder.bind(homebrew)
    }

    override fun getItemCount(): Int = homebrews.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(homebrew: HomebrewModel) {
            itemView.homebrewName.text = homebrew.name
            itemView.homebrewStyle.text = homebrew.style
        }
    }
}