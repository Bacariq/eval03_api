package com.technipixl.exo1.Comics


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technipixl.exo1.Characters.AdapterCharacters
import com.technipixl.exo1.Models.Character
import com.technipixl.exo1.Models.Item
import com.technipixl.exo1.R

class ComicsViewHolder (
    val view : View,
    private val onLikeClickListener: AdapterComics.OnLikeClickListener
) : RecyclerView.ViewHolder(view) {

    val Titre : TextView = view.findViewById(R.id.CellComicsTitre)

    public fun SetupData(cellItem : Item){
        Titre.text = cellItem.name

        view.setOnClickListener {
            onLikeClickListener.ItemOnClick(cellItem)
        }
    }
}
