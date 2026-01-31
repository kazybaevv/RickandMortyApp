package com.example.rickmortyapp.domain.repository

import com.example.rickmortyapp.domain.models.Character
import com.example.rickmortyapp.presentation.util.Either
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacter(): Flow<Either<String, Character>>
     fun getCharacterById(id: Int): Flow<Either<String, Character.Result>>
}