package com.example.rickmortyapp.presentation.Base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.rickmortyapp.presentation.util.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.Inflater


abstract class BaseActivity<VB : ViewBinding>(
    private val inflater: (LayoutInflater) -> VB
) : AppCompatActivity() {

    protected val binding: VB by lazy {
        inflater(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
        inititialize()
        setupOnClickListeners()
        setupObservers()
        setupRecyclerViews()
        setupRequests()
    }

    protected open fun inititialize() {}
    protected open fun setupObservers() {}

    protected open fun setupOnClickListeners() {}
    protected open fun setupRequests() {}
    protected open fun setupRecyclerViews() {}

    protected fun <T> StateFlow<UIState<T>>.handleState(
        onLoading: (isLoading: Boolean) -> Unit,
        onSucces: (data: T) -> Unit
    ) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@handleState.collect { state ->
                    onLoading(state is UIState.Loading)
                    when (state) {
                        is UIState.Empty -> {}

                        is UIState.Error -> {
                            Toast.makeText(this@BaseActivity, state.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is UIState.Loading -> {}

                        is UIState.Succes -> {
                            onSucces(state.data)
                        }
                    }
                }
            }
        }
    }
}
