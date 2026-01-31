package com.example.rickmortyapp.data.repository

import com.example.rickmortyapp.data.data_source.CharacterApi
import com.example.rickmortyapp.data.mappers.toChatacter
import com.example.rickmortyapp.data.mappers.toResult
import com.example.rickmortyapp.domain.models.Character
import com.example.rickmortyapp.domain.repository.CharacterRepository
import com.example.rickmortyapp.presentation.util.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import okio.IOException

class CharacterRepositoryImpl(
    private val api:
    CharacterApi
) : CharacterRepository {
    override suspend fun getCharacter(): Flow<Either<String, Character>> = flow {
        try {
            val api = api.getCharacter()
            emit(Either.Right(api.toChatacter()))
        } catch (e: IOException) {
            emit(Either.Left(e.localizedMessage ?: "Unknown error!"))
        }
    }.flowOn(Dispatchers.IO)

    override     fun getCharacterById(id: Int): Flow<Either<String, Character.Result>> = flow {
        try {
            val api = api.getCharacterById(id)
            emit(Either.Right(api.toResult()))
        } catch (e: IOException) {
            emit(Either.Left(e.localizedMessage ?: "Unknown error!"))
        }
    }.flowOn(Dispatchers.IO)


}