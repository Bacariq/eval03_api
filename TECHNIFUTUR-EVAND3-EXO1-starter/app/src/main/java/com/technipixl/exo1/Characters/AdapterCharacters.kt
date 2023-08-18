package com.technipixl.exo1.Characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.Models.Character
import com.technipixl.exo1.R

class AdapterCharacters (
    private val dataListeCharacters : MutableList<Character>,
    private val onClickListener: AdapterCharacters.OnLikeClickListener

) : RecyclerView.Adapter<CharacterViewHolder>() {

    interface OnLikeClickListener {
        fun ItemOnClick(cellItem: Character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layout =  LayoutInflater.from(parent.context).inflate(R.layout.cell_character, parent, false)
        return CharacterViewHolder(layout, onClickListener);
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item : Character = dataListeCharacters[position];
        holder.SetupData(item)
    }

    override fun getItemCount(): Int {
        return dataListeCharacters.size
    }
}