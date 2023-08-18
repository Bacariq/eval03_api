package com.technipixl.exo1.Models

import com.google.gson.annotations.SerializedName

data class MarvelResponse(
    val data: MarvelData
)
data class MarvelData(
    val results: Array<MarvelComic>
)
data class MarvelComic(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: MarvelThumbnail,
    val images: Array<MarvelImage>
)

data class MarvelThumbnail(
    val path: String,
    val extension: String
)

data class MarvelImage(
    val path: String,
    val extension: String
)



