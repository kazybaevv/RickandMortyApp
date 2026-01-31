package com.example.rickmortyapp.presentation.Base

import android.view.View
import androidx.core.view.isVisible
import com.example.rickmortyapp.presentation.util.UIState

fun <T> handleState(
    state: UIState<T>,
    progress: View,
    onSucces: (T) -> Unit,
    onError: (String) -> Unit = {}
) {
    progress.isVisible = state is UIState.Loading

    when (state) {
        is UIState.Loading -> Unit
        is UIState.Succes -> onSucces(state.data)
        is UIState.Error -> onError(state.message)

        else -> {}
    }
}