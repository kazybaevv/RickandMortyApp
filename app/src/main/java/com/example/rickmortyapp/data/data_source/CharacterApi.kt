package com.example.rickmortyapp.data.data_source

import com.example.rickmortyapp.data.model.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {

    @GET("characters")
    suspend fun getAllCharakters(): List<CharacterResponseDto>

    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterResponseDto
}