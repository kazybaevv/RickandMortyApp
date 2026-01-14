package com.example.rickmortyapp.data.repository

import com.example.rickmortyapp.data.data_source.CharacterApi
import com.example.rickmortyapp.domain.models.Character
import com.example.rickmortyapp.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api:
    CharacterApi) : CharacterRepository {
    override suspend fun getCharacters(): List<Character> {
return api.getAllCharakters().map { it.toDomain() }
    }

    override suspend fun getCharacterById(id: Int) =api.getCharacterById(id).toDomain()


}