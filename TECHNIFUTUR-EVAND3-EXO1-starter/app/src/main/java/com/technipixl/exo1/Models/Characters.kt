package com.technipixl.exo1.Models

import com.google.gson.annotations.SerializedName


data class Thumbnail(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
)

data class Item(
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
    val name: String
)

data class Comic(
    @SerializedName("items")
    val items: Array<Item>
)

data class Series(
    val resourceURI: String,
    val name: String
)

data class Story(
    val resourceURI: String,
    val name: String,
    val type: String
)

data class Event(
    val resourceURI: String,
    val name: String
)

data class Character(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("comics")
    val comics: Comic
)

data class ApiResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("data")
    val data: Data
)

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: Array<Character>
)

