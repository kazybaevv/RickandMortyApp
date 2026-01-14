package com.example.rickmortyapp.domain.repository

import com.example.rickmortyapp.domain.models.Character

interface CharacterRepository {

    suspend fun getCharacters(): List<Character>

    suspend fun getCharacterById(id: Int): Character
}