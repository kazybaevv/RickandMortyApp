package com.example.rickmortyapp.presentation.util

import org.koin.core.logger.MESSAGE

sealed class UIState<T> {
    class Loading<T> : UIState<T>()
    class Error<T>(val message: String) : UIState<T>()
    class Succes<T>(val data: T) : UIState<T>()
    class Empty<T> : UIState<T>()
}