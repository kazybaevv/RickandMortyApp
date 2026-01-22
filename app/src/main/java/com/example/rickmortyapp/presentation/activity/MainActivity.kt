package com.example.rickmortyapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rickmortyapp.databinding.ActivityMainBinding
import com.example.rickmortyapp.presentation.adapters.CharacterAdapter
import com.example.rickmortyapp.presentation.util.UIState
import com.example.rickmortyapp.presentation.viewModel.CharacterViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: CharacterViewModel by viewModel()

    private val adapter = CharacterAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        viewModel.getCharacter()
        initialize()
    }

    private fun initialize() {
        initializeAdapter()
        initializeObserver()
    }

    private fun initializeAdapter() {
        binding.characterRecyclerView.adapter = adapter
    }

    private fun initializeObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is UIState.Empty -> {

                        }

                        is UIState.Error -> {

                        }

                        is UIState.Loading -> {

                        }

                        is UIState.Succes -> {
                            adapter.submitList(state.data.results)
                        }
                    }
                }
            }
        }
    }

    fun onClick(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }
}



