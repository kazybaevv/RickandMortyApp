package com.example.rickmortyapp.presentation.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rickmortyapp.databinding.ActivityDetailBinding
import com.example.rickmortyapp.presentation.Base.BaseActivity
import com.example.rickmortyapp.presentation.util.UIState
import com.example.rickmortyapp.presentation.viewModel.СharacterDetailViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel : СharacterDetailViewModel by viewModel()
    private val id by lazy { intent.getIntExtra("ID", 0) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
        viewModel.getCharacterById(id)
        initialize()
    }
    private fun initialize(){
        initializeObserver()
    }
    private fun  initializeObserver(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.resultState.collect { state ->
                    viewModel.resultState.collect { state ->
                        when (state){
                            is UIState.Empty ->{

                            }
                            is UIState.Error ->{

                            }
                            is UIState.Loading ->{

                            }
                            is UIState.Succes ->{
                                binding.name.text = state.data.name
                            }
                        }
                    }
                }
            }
        }
    }
}


