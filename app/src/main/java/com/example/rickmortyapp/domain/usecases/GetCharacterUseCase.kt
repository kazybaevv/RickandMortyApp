package com.example.rickmortyapp.domain.usecases

import com.example.rickmortyapp.domain.models.Character
import com.example.rickmortyapp.domain.repository.CharacterRepository
import com.example.rickmortyapp.presentation.util.Either
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(): Flow<Either<String, Character>> = repository.getCharacter()
}