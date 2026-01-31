package com.example.rickmortyapp.presentation.Base

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapp.presentation.util.Either
import com.example.rickmortyapp.presentation.util.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected fun <T> Flow<Either<String, T>>.collectFlow(
        state: MutableStateFlow<UIState<T>>
    ) {
        viewModelScope.launch {
            collect { data ->
                state.value = UIState.Loading()
                when (data) {
                    is Either.Left -> {
                        state.value = UIState.Error(data.value)
                    }

                    is Either.Right -> {
                        state.value = UIState.Succes(data.value)
                    }
                }
            }
        }
    }
}