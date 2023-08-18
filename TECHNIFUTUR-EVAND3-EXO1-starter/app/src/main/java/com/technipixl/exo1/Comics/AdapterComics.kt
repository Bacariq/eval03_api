package com.technipixl.exo1.Comics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.Characters.CharacterViewHolder
import com.technipixl.exo1.Models.Character
import com.technipixl.exo1.Models.Item
import com.technipixl.exo1.R

class AdapterComics (
    private val dataListeComics : MutableList<Item>,
    private val onClickListener: AdapterComics.OnLikeClickListener

) : RecyclerView.Adapter<ComicsViewHolder>() {

    interface OnLikeClickListener {
        fun ItemOnClick(cellItem: Item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val layout =  LayoutInflater.from(parent.context).inflate(R.layout.cell_comics, parent, false)
        return ComicsViewHolder(layout, onClickListener);
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val itemCell : Item = dataListeComics[position];
        holder.SetupData(itemCell)
    }

    override fun getItemCount(): Int {
        return dataListeComics.size
    }
}