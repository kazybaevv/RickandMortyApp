package com.example.rickmortyapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickmortyapp.domain.models.Character
import com.example.rickmortyapp.domain.usecases.GetCharacterByIdUseCase
import com.example.rickmortyapp.presentation.util.Either
import com.example.rickmortyapp.presentation.util.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class СharacterDetailViewModel(private val getCharacterByIdUseCase: GetCharacterByIdUseCase) :
    ViewModel() {
    private val _resultState = MutableStateFlow<UIState<Character.Result>>(UIState.Empty())
    val resultState = _resultState.asStateFlow()

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            getCharacterByIdUseCase.invoke(id).collect { data ->
                _resultState.value = UIState.Loading()
                when (data) {
                    is Either.Left -> {
                        _resultState.value = UIState.Error(data.value)
                    }

                    is Either.Right -> {
                        _resultState.value = UIState.Succes(data.value)
                    }
                }
            }
        }
    }
}