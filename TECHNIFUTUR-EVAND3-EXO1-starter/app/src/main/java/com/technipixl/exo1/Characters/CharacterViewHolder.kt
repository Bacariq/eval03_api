package com.technipixl.exo1.Characters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technipixl.exo1.Models.Character
import com.technipixl.exo1.R

class CharacterViewHolder (
    val view : View,
    private val onLikeClickListener: AdapterCharacters.OnLikeClickListener
) : RecyclerView.ViewHolder(view) {

    val Titre : TextView = view.findViewById(R.id.CellCharacterName)
    val Img : ImageView = view.findViewById(R.id.CellCharacterImage)
    val ImgBcg : ImageView = view.findViewById(R.id.background_image)
    public fun SetupData(cellItem : Character){
        Titre.text = cellItem.name

        /*TODO a corriger */
        Glide.with(view)
            .load(cellItem.thumbnail.path + "." + cellItem.thumbnail.extension)
            .into(Img)
        //ImgBcg.
        view.setOnClickListener {
            onLikeClickListener.ItemOnClick(cellItem)
        }
    }
}