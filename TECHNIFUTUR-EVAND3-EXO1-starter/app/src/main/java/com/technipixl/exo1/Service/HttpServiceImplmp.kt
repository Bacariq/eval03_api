package com.technipixl.exo1.Service

import android.util.Log
import com.technipixl.exo1.Models.ApiResponse
import com.technipixl.exo1.Models.MarvelResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit

class HttpServiceImp {

    private fun retrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            callTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
        }.build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getCharactersList(): Response<ApiResponse> {
        val timeStamp = Date().time
        val privateKey : String= "cb822137b38f6fc4d416919de635173acb2d3427"
        val publicKey : String = "c8af9e05e64158932173e004953fb516"
        val limit : Int = 100
        val hash = HashGenerator.generateHash(timeStamp, privateKey, publicKey)
        return retrofit().create(HttpService::class.java).getCharacters(publicKey, timeStamp, hash, limit)
    }

    suspend fun getComics(id:Long): Response<ApiResponse> {
        val timeStamp = Date().time
        val privateKey : String= "cb822137b38f6fc4d416919de635173acb2d3427"
        val publicKey : String = "c8af9e05e64158932173e004953fb516"
        val hash = HashGenerator.generateHash(timeStamp, privateKey, publicKey)
        return retrofit().create(HttpService::class.java).getComics(id, publicKey, timeStamp, hash)
    }

    suspend fun getComicDetail(id:Long): Response<MarvelResponse> {

        val timeStamp = Date().time
        val privateKey : String= "cb822137b38f6fc4d416919de635173acb2d3427"
        val publicKey : String = "c8af9e05e64158932173e004953fb516"
        val hash = HashGenerator.generateHash(timeStamp, privateKey, publicKey)
        return retrofit().create(HttpService::class.java).getComicDetail(id, publicKey, timeStamp, hash)
    }
}