package com.technipixl.exo1.Service

import com.technipixl.exo1.Models.ApiResponse
import com.technipixl.exo1.Models.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HttpService {

    @GET("characters")
    suspend fun getCharacters( @Query("apikey") apikey: String,
                               @Query("ts") ts: Long,
                               @Query("hash") hash: String?,
                               @Query("limit") limit: Int?) : Response<ApiResponse>


    @GET("characters/{id}")
    suspend fun getComics( @Path("id") id: Long,
                           @Query("apikey") apikey: String,
                           @Query("ts") ts: Long,
                           @Query("hash") hash: String?) : Response<ApiResponse>
    @GET("comics/{id}")
    suspend fun getComicDetail( @Path("id") id: Long,
                           @Query("apikey") apikey: String,
                           @Query("ts") ts: Long,
                           @Query("hash") hash: String?) : Response<MarvelResponse>
}