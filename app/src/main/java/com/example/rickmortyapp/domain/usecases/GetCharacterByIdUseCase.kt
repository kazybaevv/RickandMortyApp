package com.example.rickmortyapp.domain.usecases

import com.example.rickmortyapp.domain.models.Character
import com.example.rickmortyapp.domain.repository.CharacterRepository
import com.example.rickmortyapp.presentation.util.Either
import kotlinx.coroutines.flow.Flow

class GetCharacterByIdUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Flow<Either<String, Character.Result>> =
        repository.getCharacterById(id)
}