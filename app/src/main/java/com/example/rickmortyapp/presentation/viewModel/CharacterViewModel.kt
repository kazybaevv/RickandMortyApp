package com.example.rickmortyapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapp.domain.models.Character
import com.example.rickmortyapp.domain.usecases.GetCharacterByIdUseCase
import com.example.rickmortyapp.domain.usecases.GetCharacterUseCase
import com.example.rickmortyapp.presentation.util.Either
import com.example.rickmortyapp.presentation.util.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UIState<Character>>(UIState.Empty())
    val state = _state.asStateFlow()

    fun getCharacter(){
        viewModelScope.launch {
            getCharacterUseCase.invoke().collect { data ->
                _state.value = UIState.Loading()
                when (data){
                    is Either.Left -> {
                        _state.value = UIState.Error(data.value)
                    }
                    is Either.Right ->{
                        _state.value = UIState.Succes(data.value)
                    }
                }
            }
        }
    }


}