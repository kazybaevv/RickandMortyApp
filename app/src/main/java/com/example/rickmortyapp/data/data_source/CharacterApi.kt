package com.example.rickmortyapp.data.data_source

import com.example.rickmortyapp.data.model.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {

    @GET("character")
    suspend fun getCharacter(): CharacterDto

    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDto.Result
}