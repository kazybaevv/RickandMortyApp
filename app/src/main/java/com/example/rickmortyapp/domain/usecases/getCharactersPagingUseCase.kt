package com.example.rickmortyapp.domain.usecases

class GetCharactersPagingUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flow<PagingData<Character>> = repository.getCharactersPaging()
}