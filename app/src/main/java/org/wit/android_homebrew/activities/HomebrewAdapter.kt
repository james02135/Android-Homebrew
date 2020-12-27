package org.wit.android_homebrew.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_homebrew.view.*
import kotlinx.android.synthetic.main.card_homebrew.view.*
import kotlinx.android.synthetic.main.card_homebrew.view.homebrewName
import kotlinx.android.synthetic.main.card_homebrew.view.homebrewStyle
import org.wit.android_homebrew.R
import org.wit.android_homebrew.models.HomebrewModel

interface HomebrewListener {
    fun onHomebrewClick(homebrew: HomebrewModel)
}

class HomebrewAdapter constructor(
        private var homebrews: List<HomebrewModel>,
        private val listener: HomebrewListener
        ) : RecyclerView.Adapter<HomebrewAdapter.MainHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MainHolder {
        return MainHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.card_homebrew,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(
            holder: MainHolder,
            position: Int
    ) {
        val homebrew = homebrews[holder.adapterPosition]
        holder.bind(homebrew, listener)
    }

    override fun getItemCount(): Int = homebrews.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(homebrew: HomebrewModel, listener: HomebrewListener) {
            itemView.homebrewName.text = homebrew.name
            itemView.homebrewStyle.text = homebrew.style
            itemView.setOnClickListener { listener.onHomebrewClick(homebrew)}
        }
    }
}