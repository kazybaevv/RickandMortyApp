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
import com.example.rickmortyapp.domain.models.Character
import com.example.rickmortyapp.presentation.Base.BaseActivity
import com.example.rickmortyapp.presentation.adapters.CharacterAdapter
import com.example.rickmortyapp.presentation.util.UIState
import com.example.rickmortyapp.presentation.viewModel.CharacterViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val viewModel: CharacterViewModel by viewModel()

    private val adapter = CharacterAdapter(this)



    override fun setupRecyclerViews() {
        super.setupRecyclerViews()
        binding.characterRecyclerView.adapter = adapter
    }


    override fun setupObservers() {
        viewModel.state.handleState(
            onLoading = { isVisible ->
                binding.progress.isVisible = isVisible
            },
            onSucces = { data ->
                adapter.submitList(data as List<Character.Result?>?)
            }
        )
    }

    private fun onCharacterClick(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }
}



