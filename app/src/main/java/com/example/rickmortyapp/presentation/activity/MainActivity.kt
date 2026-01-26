package com.example.rickmortyapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
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
                            binding.progress.isVisible = true
                            binding.characterRecyclerView.isVisible = true
                        }

                        is UIState.Error -> {
                            binding.progress.isVisible = false
                            binding.characterRecyclerView.isVisible = false
                        }

                        is UIState.Loading -> {
                            binding.progress.isVisible = true
                            binding.characterRecyclerView.isVisible = false
                        }

                        is UIState.Succes -> {
                            binding.progress.isVisible = false
                            binding.characterRecyclerView.isVisible = true
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



