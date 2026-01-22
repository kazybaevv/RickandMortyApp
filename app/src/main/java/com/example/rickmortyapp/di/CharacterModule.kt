package com.example.rickmortyapp.di

import com.example.rickmortyapp.data.data_source.CharacterApi
import com.example.rickmortyapp.data.repository.CharacterRepositoryImpl
import com.example.rickmortyapp.domain.repository.CharacterRepository
import com.example.rickmortyapp.domain.usecases.GetCharacterByIdUseCase
import com.example.rickmortyapp.domain.usecases.GetCharacterUseCase
import com.example.rickmortyapp.presentation.viewModel.CharacterViewModel
import com.example.rickmortyapp.presentation.viewModel.СharacterDetailViewModel
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory


val appModule: List<Module> get() = listOf(dataModule, domainModule, presentationModule)

private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

private const val BASE_URL = "https://rickandmortyapi.com/api/character"

val dataModule = module {
    single {
        json.asConverterFactory("application/json".toMediaType())
    }
    single<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
            .create(CharacterApi::class.java)
    }
    single<CharacterRepository> {
        CharacterRepositoryImpl(api = get())
    }
}

val domainModule = module {
    factory { GetCharacterByIdUseCase(get()) }
    factory { GetCharacterUseCase(get()) }
}

val presentationModule = module {
    viewModel {
        CharacterViewModel(getCharacterUseCase = get())
    }
    viewModel {
        СharacterDetailViewModel(getCharacterByIdUseCase = get())
    }
}